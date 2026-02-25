package pages;

import annotations.Path;
import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage {

    @Inject
    public CatalogPage(Page page) {
        super(page);
    }


    public boolean selectedDirection() {
        return page.getByLabel("Все направления").isChecked();
    }

    public boolean selectedLevel() {
        return page.getByLabel("Любой уровень").isChecked();
    }

    public void selectDuration3to10() {

        Locator sliders = page.locator("[role='slider']");
        Locator left = sliders.nth(0);
        Locator right = sliders.nth(1);
        left.focus();
        left.press("ArrowRight");
        left.press("ArrowRight");
        left.press("ArrowRight");
        right.focus();
        for (int i = 0; i < 5; i++) {
            right.press("ArrowLeft");
        }
    }

    public int getCoursesCount() {
        return page.locator("a[href^='/lessons/']:visible").count();
    }

    public void selectArchitectureDirection() {
        page.locator("label", new Page.LocatorOptions().setHasText("Архитектура"))
                .click();
    }

    public void resetFilter() {
        page.getByText("Очистить фильтры").click();
    }

    public Locator courseCards() {
        return page.locator("a[href^='/lessons/']");
    }

    public List<String> getCourseMetaInfo() {
        return courseCards()
                .locator("div:has-text('·')")
                .allInnerTexts();
    }

    public List<String> getCourseTitles() {
        return page.locator("div:has(span:has-text('Курс')) h3")
                .allInnerTexts();
    }
}
