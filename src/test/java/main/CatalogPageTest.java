package main;

import com.google.inject.Inject;
import com.microsoft.playwright.Frame;
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

        // 1️⃣ Default filters
        Assertions.assertTrue(catalogPage.selectedDirection());
        Assertions.assertTrue(catalogPage.selectedLevel());

        // Save initial titles
        List<String> initialTitles = catalogPage.getCourseTitles();

        // 2️⃣ Duration 3-10
        catalogPage.selectDuration3to10();

        List<String> metaInfos = catalogPage.getCourseMetaInfo();
        Assertions.assertFalse(metaInfos.isEmpty());

        for (String meta : metaInfos) {
            String monthsPart = meta.split("·")[1].trim();
            int months = Integer.parseInt(monthsPart.replaceAll("\\D+", ""));
            Assertions.assertTrue(months >= 3 && months <= 10);
        }

        // 3️⃣ Save first card BEFORE Architecture
        // 3️⃣ Architecture
        int countBefore = catalogPage.getCoursesCount();

        catalogPage.selectArchitectureDirection();

// սպասում ենք որ կարտչկաները թարմացվեն
        page.waitForLoadState();

        int countAfter = catalogPage.getCoursesCount();

      //  Assertions.assertNotEquals(countBefore, countAfter);


        // 4️⃣ Reset
        catalogPage.resetFilter();
        page.waitForLoadState();

        List<String> resetTitles = catalogPage.getCourseTitles();

  }

}