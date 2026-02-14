package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import com.google.inject.Inject;
import java.util.List;

@Path("/catalog/courses")
public class CatalogPage extends  AbsBasePage{

    @Inject
    public CatalogPage(Page page) {
        super(page);
    }


    // --- Filters ---

    public String selectedDirection() {
        return page.locator("[data-testid='direction-filter'] .selected")
                .innerText()
                .trim();
    }

    public String selectedLevel() {
        return page.locator("[data-testid='level-filter'] .selected")
                .innerText()
                .trim();
    }

    public void selectDuration3to10() {
        page.getByText("3-10 месяцев").click();
    }

    public void selectArchitectureDirection() {
        page.getByText("Архитектура").click();
    }

    public void resetFilter() {
        page.getByText("Сбросить фильтр").click();
    }

    // --- Courses ---

    public Locator courseCards() {
        return page.locator(".course-card");
    }

    public List<String> getCourseDurations() {
        return page.locator(".course-card .course-duration")
                .allInnerTexts();
    }

    public List<String> getCourseTitles() {
        return page.locator(".course-card h3")
                .allInnerTexts();
    }
}
