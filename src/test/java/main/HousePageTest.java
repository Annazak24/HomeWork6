package main;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HousePage;

@ExtendWith(PlaywrightUiExtension.class)
public class HousePageTest {

    @Inject
    private HousePage housePage;

    @Inject
    protected Page page;

    @Test
    void scenario1_testTeachersSwiper() {

        housePage.open();
        housePage.scrollToTeachers();
        housePage.waitForTeachers();
        Assertions.assertTrue(housePage.teachers().count() > 0,
                "Teachers list is empty");

        String beforeDrag = housePage.getActiveTeacherName();
        housePage.dragTeachers();
        String afterDrag = housePage.getActiveTeacherName();
        Assertions.assertNotEquals(beforeDrag, afterDrag,
                "Swiper did not move after drag");

        housePage.clickActiveTeacher();
        String expectedName = housePage.getActiveTeacherName();
        String popupName = housePage.popupTitle();
        Assertions.assertEquals(expectedName, popupName,
                "Wrong popup opened");

        String firstPopupTitle = housePage.popupTitle();
        housePage.nextTeacher();
        page.waitForTimeout(500);
        String secondPopupTitle = housePage.popupTitle();
        Assertions.assertNotEquals(firstPopupTitle, secondPopupTitle,
                "Next teacher did not open different card");


        housePage.previousTeacher();
        page.waitForTimeout(500);
        String backPopupTitle = housePage.popupTitle();
        Assertions.assertEquals(firstPopupTitle, backPopupTitle,
                "Previous teacher did not return to first card");
    }
}
