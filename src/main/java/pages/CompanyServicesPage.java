package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.google.inject.Inject;

import java.util.List;

@Path("/uslugi-kompaniyam")
public class CompanyServicesPage extends AbsBasePage {

@Inject
    public CompanyServicesPage(Page page) {
        super(page);
    }

    // --- Block "Не нашли нужный курс?" ---

    public void clickMoreDetails() {
        page.getByText("Не нашли нужный курс?")
                .locator("..")
                .getByText("Подробнее")
                .click();
    }

    // --- Business course page verification ---

    public boolean isBusinessCoursePageOpened() {
        return page.title().contains("Разработка курса для бизнеса")
                || page.url().contains("razrabotka");
    }

    // --- Directions block ---

    public Locator directions() {
        return page.locator("a[href*='catalog']");
    }

    public List<String> getDirectionsText() {
        return directions().allInnerTexts();
    }

    public String clickFirstDirection() {
        Locator first = directions().first();
        String directionName = first.innerText().trim();
        first.click();
        return directionName;
    }

    // --- After direction click (catalog) ---

    public boolean isCatalogOpened() {
        return page.url().contains("/catalog");
    }

    public String selectedCategory() {
        return page.locator(".selected")
                .first()
                .innerText()
                .trim();
    }
}
