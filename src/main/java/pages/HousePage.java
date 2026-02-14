package pages;

import annotations.Path;
import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Mouse;

@Path("/lessons/clickhouse/")
public class HousePage extends AbsBasePage {
    @Inject
    public HousePage(Page page) {
        super(page);
    }

    public Locator teachers() {
        return page.locator(
                "section:has(h2:has-text('Преподаватели')) .swiper-slide"
        );
    }



    public void dragTeachers() {

        Locator wrapper = page.locator(".swiper-wrapper");

        wrapper.waitFor();

        // Get bounding box of swiper
        var box = wrapper.boundingBox();

        double startX = box.x + box.width - 20;  // start near right edge
        double endX = box.x + 20;                // drag to left
        double y = box.y + box.height / 2;

        page.mouse().move(startX, y);
        page.mouse().down();
        page.mouse().move(endX, y, new Mouse.MoveOptions().setSteps(15));
        page.mouse().up();
    }




    public void clickTeacher(int index) {
        teachers().nth(index).click();
    }

    public String popupTitle() {
        return page.locator(".popup-title").innerText();
    }

    public void nextTeacher() {
        page.locator(".popup-next").click();
    }

    public void previousTeacher() {
        page.locator(".popup-prev").click();
    }

    public void acceptCookiesIfPresent() {
        Locator button = page.locator("text=Принять");
        if (button.isVisible()) {
            button.click();
        }
    }

    public void scrollToTeachers() {
        page.locator("h2:has-text('Преподаватели')")
                .scrollIntoViewIfNeeded();
    }


    public void waitForTeachers() {
        teachers().first().waitFor();
    }

    public void goToNextSlide() {
        page.locator(".swiper-pagination-bullet")
                .nth(1)
                .click();
    }


    public String getActiveTeacherName() {
        return page.locator(".swiper-slide-active p")
                .first()
                .innerText()
                .trim();
    }



    public void clickActiveTeacher() {
        page.locator(".swiper-slide-active").click();
    }

    public void waitForAnimation() {
        page.waitForTimeout(800);
    }


}
