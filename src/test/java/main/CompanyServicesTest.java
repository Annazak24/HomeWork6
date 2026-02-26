package main;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CompanyServicesPage;

@ExtendWith(PlaywrightUiExtension.class)
public class CompanyServicesTest {

    @Inject
    CompanyServicesPage companyServicesPage;

    @Inject
    private Page page;

    @Test
    void scenario3() {

        companyServicesPage.open();

        companyServicesPage.clickMoreDetails();

        Locator depositsBlock = page.locator("#deposits");
        depositsBlock.waitFor();

        Assertions.assertTrue(depositsBlock.isVisible());

        Assertions.assertTrue(companyServicesPage.isBusinessCoursePageOpened());

        Assertions.assertFalse(companyServicesPage.getDirectionsText().isEmpty());

        String clickedDirection = companyServicesPage.clickFirstDirection();

        Assertions.assertTrue(companyServicesPage.isCatalogOpened());

        Assertions.assertEquals(clickedDirection, companyServicesPage.selectedCategory());
    }
}
