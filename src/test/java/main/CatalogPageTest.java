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


        List<String> initialTitles = catalogPage.getCourseTitles();
        catalogPage.selectDuration3to10();
        List<String> metaInfos = catalogPage.getCourseMetaInfo();
        Assertions.assertFalse(metaInfos.isEmpty());
        for (String meta : metaInfos) {
            String monthsPart = meta.split("·")[1].trim();
            int months = Integer.parseInt(monthsPart.replaceAll("\\D+", ""));
            page.waitForURL("**duration=3-10**");
            Assertions.assertTrue(
                    months >= 3 && months <= 10,
                    "Course duration is outside filter range: " + meta
            );
        }


        int countBefore = catalogPage.getCoursesCount();
        catalogPage.selectArchitectureDirection();
        page.waitForCondition(() -> catalogPage.getCoursesCount() != countBefore);
        int countAfter = catalogPage.getCoursesCount();
        Assertions.assertNotEquals(countBefore, countAfter);


        catalogPage.resetFilter();
        page.waitForLoadState();
        List<String> resetTitles = catalogPage.getCourseTitles();

    }
}