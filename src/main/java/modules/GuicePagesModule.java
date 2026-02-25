package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.microsoft.playwright.Page;
import pages.MainPage;

public class GuicePagesModule extends AbstractModule {

    private Page page;

    public GuicePagesModule(Page page) {
        this.page = page;
    }

    @Provides
    public MainPage mainPage() {
        return new MainPage(page);
    }

}
