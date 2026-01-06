package pages;

import annotations.Path;
import java.util.List;
import java.util.Random;

import jakarta.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
@Inject
   public MainPage(GuiceScoped guiceScoped) {
      super(guiceScoped);
   }


   @FindBy(xpath = "//span[text()=\"Обучение\"]")
   private WebElement training;

   @FindBy(xpath = "//div[contains(@class, 'lhsLfs')]//a")
   private List<WebElement> categories;


   public MainPage hoverTraining() {
      actions.moveToElement(training).perform();
      return this;
   }

   public String clickRandomCategory() {
      if (categories.isEmpty()) {
         throw new IllegalStateException("No categories found");
      }

      int randomIndex = new Random().nextInt(categories.size());
      String categoriesName = categories.get(randomIndex).getText();
      categories.get(randomIndex).click();

      return categoriesName;
   }
}
