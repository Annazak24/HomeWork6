package pages;

import annotations.Path;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


@Path("/")
public class MainPage extends AbsBasePage {

    public MainPage(Page page) {
        super(page);
    }

    public String getNewsLinkTitle(int index) {
        return page.locator("[data-qa='NewsMainList'] a")
                .nth(--index)
                .innerText();
    }

    public void clickNewsLink(String title) {
        page.getByRole(AriaRole.LINK,
                        new Page.GetByRoleOptions().setName(title))
                .click();
    }

}
