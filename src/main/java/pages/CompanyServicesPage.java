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


    public void clickMoreDetails() {
        Locator block = page.getByText("Не нашли нужный курс?")
                .locator("..");

        Locator moreDetails = block.locator("a[href='#deposits']");

        moreDetails.scrollIntoViewIfNeeded();
        moreDetails.click();
    }


    public boolean isBusinessCoursePageOpened() {
        return page.title().contains("Разработка курса для бизнеса")
                || page.url().contains("razrabotka");
    }


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
