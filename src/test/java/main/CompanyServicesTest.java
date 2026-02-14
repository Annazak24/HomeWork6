package main;

import com.google.inject.Inject;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CompanyServicesPage;

@ExtendWith(PlaywrightUiExtension.class)
public class CompanyServicesTest {

    @Inject
    CompanyServicesPage companyServicesPage;

    @Test
    void scenario3() {

        companyServicesPage.open();

        companyServicesPage.clickMoreDetails();

        Assertions.assertTrue(companyServicesPage.isBusinessCoursePageOpened());

        Assertions.assertFalse(companyServicesPage.getDirectionsText().isEmpty());

        String clickedDirection = companyServicesPage.clickFirstDirection();

        Assertions.assertTrue(companyServicesPage.isCatalogOpened());

        Assertions.assertEquals(clickedDirection, companyServicesPage.selectedCategory());
    }
}
