package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.microsoft.playwright.Page;

public class GuiceModule extends AbstractModule {

    private Page page;

    public GuiceModule(Page page) {
        this.page = page;
    }

    @Provides
    public Page getPage() {
        return page;
    }
}
