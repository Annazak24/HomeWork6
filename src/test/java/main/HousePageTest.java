package main;

import com.google.inject.Inject;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HousePage;

@ExtendWith(PlaywrightUiExtension.class)
public class HousePageTest {

    @Inject
    HousePage housePage;

    @Test
    void housePageTest() {

        // Open page
        housePage.open();

        // Scroll to teachers block
        housePage.scrollToTeachers();

        // Wait until teachers are loaded
        housePage.waitForTeachers();

        // Verify teachers are displayed
        Assertions.assertTrue(
                housePage.teachers().count() > 0,
                "Teachers list is empty"
        );

        // Save active teacher before drag
        String beforeDrag = housePage.getActiveTeacherName();

        // Drag teachers slider
        housePage.dragTeachers();

        // Small wait for swiper animation
        housePage.waitForAnimation();

        // Get active teacher after drag
        String afterDrag = housePage.getActiveTeacherName();

        // Verify slider moved
        Assertions.assertNotEquals(
                beforeDrag,
                afterDrag,
                "Swiper did not move after drag"
        );

        // Click active teacher
        housePage.clickActiveTeacher();

        String firstPopupTitle = housePage.popupTitle();

        // Go to next teacher
        housePage.nextTeacher();
        housePage.waitForAnimation();

        String secondPopupTitle = housePage.popupTitle();

        // Verify next teacher opened
        Assertions.assertNotEquals(
                firstPopupTitle,
                secondPopupTitle,
                "Next teacher popup did not change"
        );

        // Go back to previous teacher
        housePage.previousTeacher();
        housePage.waitForAnimation();

        // Verify p
    }
}