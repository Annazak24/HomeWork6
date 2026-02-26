package main;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogPage;

import java.util.List;

@ExtendWith(PlaywrightUiExtension.class)
public class CatalogPageTest {

    @Inject
    private CatalogPage catalogPage;

    @Inject
    private Page page;

    @Test
    void scenario2() {


        catalogPage.open();
        Assertions.assertTrue(catalogPage.selectedDirection());
        Assertions.assertTrue(catalogPage.selectedLevel());


        int initialCount = catalogPage.getCoursesCount();
        catalogPage.selectDuration3to10();
        page.waitForCondition(() ->
                catalogPage.getCoursesCount() != initialCount);


        List<String> metaInfos = catalogPage.getCourseMetaInfo();
        Assertions.assertFalse(metaInfos.isEmpty());


        for (String meta : metaInfos) {
            String monthsPart = meta.split("·")[1].trim();
            int months = Integer.parseInt(monthsPart.replaceAll("\\D+", ""));

            Assertions.assertTrue(
                    months >= 3 && months <= 10,
                    "Course duration is outside filter range: " + meta);
        }

        int countBeforeDirection = catalogPage.getCoursesCount();
        catalogPage.selectArchitectureDirection();

        page.waitForCondition(() ->
                catalogPage.getCoursesCount() != countBeforeDirection);
        int countAfterDirection = catalogPage.getCoursesCount();
        Assertions.assertNotEquals(countBeforeDirection, countAfterDirection);


        catalogPage.resetFilter();
        page.waitForLoadState();
    }
}