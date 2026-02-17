package pages;

import annotations.Path;
import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.options.BoundingBox;


@Path("/lessons/clickhouse/")
public class HousePage extends AbsBasePage {

    @Inject
    public HousePage(Page page) {
        super(page);
    }

    private Locator teachersSection() {
        return page.locator("h2:has-text('Преподаватели')")
                .locator("..")      // parent
                .locator("..");     // wrapper
    }

    private Locator teachersSwiper() {
        return teachersSection()
                .locator(".swiper-wrapper")
                .first();
    }

    public Locator teachers() {
        return page.locator(".swiper-slide");
    }

    public void scrollToTeachers() {
        teachersSection().scrollIntoViewIfNeeded();
    }

    public void waitForTeachers() {
        teachers().first().waitFor();
    }

    public String getActiveTeacherName() {
        return page.locator(".swiper-slide-active p.sc-1s527z5-1").innerText();
    }

    public void dragTeachers() {

        Locator teachersBlock = page
                .locator("h2:has-text('Преподаватели')")
                .locator("xpath=ancestor::section");

        Locator wrapper = teachersBlock.locator(".swiper-wrapper").first();
        BoundingBox box = wrapper.boundingBox();

        double startX = box.x + box.width - 50;
        double startY = box.y + box.height / 2;

        double endX = box.x + 50;
        double endY = startY;

        page.mouse().move(startX, startY);
        page.mouse().down();
        page.mouse().move(endX, endY, new Mouse.MoveOptions().setSteps(20));
        page.mouse().up();
    }

    public void clickActiveTeacher() {
        teachersSwiper()
                .locator(".swiper-slide-active")
                .click();
    }

    public String popupTitle() {

        Locator popupTitle = page.locator(
                "#__PORTAL__ .swiper-slide-active .sc-1xbggqf-2 h3");
        popupTitle.waitFor();
        return popupTitle.innerText().trim();
    }

    private Locator popup() {
        return page.locator("#__PORTAL__");
    }

    public void nextTeacher() {
        popup()
                .locator("button.sc-1bkbgbz-4")
                .click(new Locator.ClickOptions().setForce(true));
    }

    public void previousTeacher() {
        popup()
                .locator("button.sc-1bkbgbz-3")
                .click(new Locator.ClickOptions().setForce(true));
    }
}
