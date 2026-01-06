package pages;

import annotations.Path;
import dto.CourseInfo;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.GuiceScoped;
import waiters.Waiter;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage {
    private final GuiceScoped guiceScoped;

    @Inject
    public CatalogPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
        this.guiceScoped = guiceScoped;

        PageFactory.initElements(guiceScoped.getDriver(), this);
    }
   @FindBy(xpath = "//p[normalize-space(text())='Направление']" +
       "/ancestor::div[contains(@class,'sc-1w8jhjp-1')]" +
       "/following-sibling::div" +
       "//div[contains(@class,'sc-1fry39v-0') and @value='true']//label")
   private WebElement activeCategory;

public  void openCatalogPage(){
    guiceScoped.getDriver().get( System.getProperty("base.url") + "/catalog/courses");
}


   public void clickCourseByName(String courseName) {
      By courseLocator = By.xpath(
          "//div[contains(@class,'sc-hrqzy3-1') and contains(@class,'jEGzDf') and normalize-space(text())='"
              + courseName + "']"
      );

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

      try {
         WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(courseLocator));
         scrollAndHighlight(course);
         wait.until(ExpectedConditions.elementToBeClickable(course)).click();
      } catch (StaleElementReferenceException e) {
         WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(courseLocator));
         scrollAndHighlight(course);
         wait.until(ExpectedConditions.elementToBeClickable(course)).click();
      } catch (TimeoutException e) {
         throw new NoSuchElementException("Course is not found " + courseName);
      }
   }

   public String getCourseTitleByJsoup() {
      String html = driver.getPageSource();
      Document doc = Jsoup.parse(html);
      return doc.select("h1").text().trim();
   }

   public List<CourseInfo> getAllCourses() {

       Waiter waiter = new Waiter(driver);
       By cardLocator = By.xpath("//a[contains(@class,'sc-zzdkm7-0')]");
       waiter.waitForCondition(
               ExpectedConditions.visibilityOfElementLocated(cardLocator)
       );

      List<CourseInfo> courses = new ArrayList<>();
      List<WebElement> courseCards = driver.findElements(
          By.xpath("//a[contains(@class,'sc-zzdkm7-0')]"));

      for (WebElement card : courseCards) {
         try {

            WebElement titleElement = card.findElement(
                By.xpath(".//h6//div[contains(@class,'jEGzDf')]"));
            String name = titleElement.getText().trim();

            WebElement dateElement = card.findElement(
                By.xpath(".//div[contains(@class,'jEGzDf') and contains(.,'месяц')]"));
            String dateText = dateElement.getText().trim();

            LocalDate parsedDate = parseDate(dateText);
            if (parsedDate == null) {
               continue;
            }

            courses.add(new CourseInfo(name, parsedDate, titleElement));


         } catch (Exception e) {
            System.out.println("Skipped " + e.getMessage());
         }
      }

      return courses;
   }


   public Map<String, List<String>> getEarliestAndLatestCourseNames(List<CourseInfo> courses) {
      if (courses == null || courses.isEmpty()) {
         throw new IllegalArgumentException("Empty courses list");
      }

      LocalDate earliestDate = courses.stream()
          .map(CourseInfo::getDate)
          .reduce((d1, d2) -> d1.isBefore(d2) ? d1 : d2)
          .orElseThrow();

      LocalDate latestDate = courses.stream()
          .map(CourseInfo::getDate)
          .reduce((d1, d2) -> d1.isAfter(d2) ? d1 : d2)
          .orElseThrow();

      List<String> earliestCourses = courses.stream()
          .filter(c -> c.getDate().isEqual(earliestDate))
          .map(CourseInfo::getName)
          .toList();

      List<String> latestCourses = courses.stream()
          .filter(c -> c.getDate().isEqual(latestDate))
          .map(CourseInfo::getName)
          .toList();

      Map<String, List<String>> result = new LinkedHashMap<>();
      result.put("earliest", earliestCourses);
      result.put("latest", latestCourses);

      return result;
   }

   private static final DateTimeFormatter RUS_DATE_FORMATTER = new DateTimeFormatterBuilder()
       .parseCaseInsensitive()
       .appendPattern("d MMMM yyyy")
       .toFormatter(new Locale("ru"));

   private LocalDate parseDate(String text) {
      if (text == null || text.isBlank()) {
         return null;
      }

      try {
         String clean = text
             .replaceAll("[·•]", " ")
             .replaceAll("месяц(ев|а)?", "")
             .replaceAll(",", "")
             .trim();

         Matcher matcher = Pattern.compile("\\d{1,2}\\s+\\p{IsCyrillic}+\\s+\\d{4}").matcher(clean);
         if (matcher.find()) {
            String datePart = matcher.group().trim();
            LocalDate parsed = LocalDate.parse(datePart, RUS_DATE_FORMATTER);
            return parsed;
         } else {
            System.out.println("Pattern not found in: " + text);
         }

      } catch (Exception e) {
         System.out.println("Failed to pars " + text + " → " + e.getMessage());
      }
      return null;
   }

   private void scrollAndHighlight(WebElement element) {
      ((JavascriptExecutor) driver).executeScript(
          "arguments[0].scrollIntoView({block:'center'}); " +
              "arguments[0].style.border='3px solid red'; " +
              "arguments[0].style.transition='0.3s';", element);
   }


   public String getCategoryName() {
       return activeCategory.getText();
   }

    public void searchCoursesStartingFromDate(String date) {
       CatalogPage catalogPage= new CatalogPage(guiceScoped);


        LocalDate targetDate = LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );

        var filteredCourses = catalogPage.getAllCourses().stream()
                .filter(course ->
                        course.getDate() != null &&
                                !course.getDate().isBefore(targetDate)
                )
                .toList();

        guiceScoped.store(filteredCourses,"filteredCourses");
    }


    public void printCoursesInfoToConsole() {

        List<?> courses = guiceScoped.retriver("filteredCourses");

        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses found for the given date");
            return;
        }

        System.out.println("Courses starting from selected date:");

        courses.forEach(course -> {
            var c = (dto.CourseInfo) course;
            System.out.printf(
                    "• %s | Start date: %s%n",
                    c.getName(),
                    c.getDate()
            );
        });
    }
}
