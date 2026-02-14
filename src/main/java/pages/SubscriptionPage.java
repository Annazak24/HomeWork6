package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.google.inject.Inject;

import java.util.List;

@Path("/subscription")
public class SubscriptionPage extends AbsBasePage {

    @Inject
    public SubscriptionPage(Page page) {
        super(page);
    }

    // =========================
    // 1️⃣ Subscription cards
    // =========================

    public Locator subscriptionCards() {
        return page.locator("div").filter(new Locator.FilterOptions()
                .setHasText("Купить"));
    }

    public boolean areSubscriptionsVisible() {
        subscriptionCards().first().waitFor();
        return subscriptionCards().count() > 0;
    }

    // =========================
    // 2️⃣ Expand description
    // =========================

    public void clickMoreDetails() {

        Locator moreLink = page.getByText("Подробнее").first();

        moreLink.waitFor();
        moreLink.click();
    }

    public boolean isExpanded() {
        return page.getByText("Свернуть").first().isVisible();
    }

    // =========================
    // 3️⃣ Collapse description
    // =========================

    public void clickCollapse() {

        Locator collapse = page.getByText("Свернуть").first();

        collapse.waitFor();
        collapse.click();
    }

    public boolean isCollapsed() {
        return page.getByText("Подробнее").first().isVisible();
    }

    // =========================
    // 4️⃣ Buy button
    // =========================

    public void clickBuy() {

        Locator buyButton = page.getByText("Купить").first();

        buyButton.waitFor();
        buyButton.click();

        page.waitForLoadState();
    }

    public boolean isPaymentPageOpened() {
        return page.url().contains("payment")
                || page.title().toLowerCase().contains("оплата");
    }

    // =========================
    // 5️⃣ Payment page data
    // =========================

    public String getPrice() {

        Locator price = page.locator("[class*='price']").first();
        price.waitFor();

        return price.innerText().trim();
    }

    public String getDuration() {

        Locator duration = page.locator("[class*='duration']").first();
        duration.waitFor();

        return duration.innerText().trim();
    }

    public void selectTrial() {

        Locator trialOption = page.getByText("Trial").first();

        trialOption.waitFor();
        trialOption.click();

        page.waitForTimeout(1000); // wait for price recalculation
    }
}
