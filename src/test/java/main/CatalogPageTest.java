package main;

import com.google.inject.Inject;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogPage;

import java.util.List;

@ExtendWith(PlaywrightUiExtension.class)
public class CatalogPageTest {

    @Inject
    CatalogPage catalogPage;

    @Test
    void scenario2() {

        catalogPage.open();

        // 1️⃣ Default filters check
        Assertions.assertEquals("Все направления", catalogPage.selectedDirection());
        Assertions.assertEquals("Любой уровень сложности", catalogPage.selectedLevel());

        // Save initial catalog
        List<String> initialTitles = catalogPage.getCourseTitles();

        // 2️⃣ Select duration
        catalogPage.selectDuration3to10();

        // Verify duration
        List<String> durations = catalogPage.getCourseDurations();

        for (String duration : durations) {
            Assertions.assertTrue(
                    duration.contains("3") ||
                            duration.contains("4") ||
                            duration.contains("5") ||
                            duration.contains("6") ||
                            duration.contains("7") ||
                            duration.contains("8") ||
                            duration.contains("9") ||
                            duration.contains("10"),
                    "Invalid duration: " + duration
            );
        }

        // 3️⃣ Select Architecture
        catalogPage.selectArchitectureDirection();

        List<String> architectureTitles = catalogPage.getCourseTitles();

        Assertions.assertNotEquals(initialTitles, architectureTitles);

        // 4️⃣ Reset filter
        catalogPage.resetFilter();

        Assertions.assertEquals("Все направления", catalogPage.selectedDirection());

        List<String> resetTitles = catalogPage.getCourseTitles();

        Assertions.assertNotEquals(architectureTitles, resetTitles);
    }
}
