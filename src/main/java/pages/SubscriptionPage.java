package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.google.inject.Inject;


@Path("/subscription")
public class SubscriptionPage extends AbsBasePage {

    @Inject
    public SubscriptionPage(Page page) {
        super(page);
    }

    public Locator subscriptionCards() {
        return page.locator("div").filter(new Locator.FilterOptions()
                .setHasText("Купить"));
    }

    public boolean areSubscriptionsVisible() {
        subscriptionCards().first().waitFor();
        return subscriptionCards().count() > 0;
    }

    public void clickMoreDetails() {

        Locator moreLink = page.getByText("Подробнее").first();

        moreLink.waitFor();
        moreLink.click();
    }

    public boolean isExpanded() {
        return page.getByText("Свернуть").first().isVisible();
    }

    public void clickCollapse() {

        Locator collapse = page.getByText("Свернуть").first();

        collapse.waitFor();
        collapse.click();
    }

    public boolean isCollapsed() {
        return page.getByText("Подробнее").first().isVisible();
    }


    public void clickBuy() {
        Locator buyButton = page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Купить")).first();

        buyButton.waitFor();
        buyButton.click();
    }


    public boolean isPaymentPageOpened() {
        return page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Оплатить обучение")).isVisible();
    }


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

        page.waitForTimeout(1000);
    }
}
