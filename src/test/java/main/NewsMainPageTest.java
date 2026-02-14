package main;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import extensions.PlaywrightUiExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;
import pages.NewsPage;

@ExtendWith(PlaywrightUiExtension.class)
public class NewsMainPageTest {

    @Inject
    private Page page;

    @Inject
    private MainPage mainPage;

    @Inject
    private NewsPage newsPage;

    @Test
    public void clickNewsLink() {
        mainPage.open();
        String title = mainPage.getNewsLinkTitle(1);
        mainPage.clickNewsLink(title);

        newsPage.headlineCheck(title);
    }
}
