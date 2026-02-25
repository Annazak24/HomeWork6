package main;

import com.google.inject.Inject;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SubscriptionPage;


@ExtendWith(PlaywrightUiExtension.class)
public class SubscriotionPageTest {
    @Inject
    private SubscriptionPage subscriptionPage;

    void scenario4() {

        subscriptionPage.open();

        Assertions.assertTrue(subscriptionPage.areSubscriptionsVisible());

        subscriptionPage.clickMoreDetails();
        Assertions.assertTrue(subscriptionPage.isExpanded());

        subscriptionPage.clickCollapse();
        Assertions.assertTrue(subscriptionPage.isCollapsed());

        subscriptionPage.clickBuy();
        Assertions.assertTrue(subscriptionPage.isPaymentPageOpened());

        String initialPrice = subscriptionPage.getPrice();
        String initialDuration = subscriptionPage.getDuration();

        subscriptionPage.selectTrial();

        String trialPrice = subscriptionPage.getPrice();
        String trialDuration = subscriptionPage.getDuration();

        Assertions.assertNotEquals(initialPrice, trialPrice);
        Assertions.assertNotEquals(initialDuration, trialDuration);
    }
}
