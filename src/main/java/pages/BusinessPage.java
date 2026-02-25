package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BusinessPage extends AbsBasePage{
    public BusinessPage(Page page) {
        super(page);


        page.getByRole(AriaRole.LINK,
                        new Page.GetByRoleOptions().setName("Подробнее"))
                .click();

    }
}
