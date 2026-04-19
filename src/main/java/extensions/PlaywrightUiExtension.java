package extensions;

import com.google.inject.Guice;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import modules.GuiceModule;
import modules.GuicePagesModule;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class PlaywrightUiExtension implements BeforeAllCallback, BeforeEachCallback,
      AfterEachCallback, AfterAllCallback {

   private Playwright playwright;
   private Browser browser;
   private BrowserContext browserContext;
   private Page page;

   @Override
   public void beforeAll(ExtensionContext context) {
      playwright = Playwright.create();

      browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                  .setHeadless(true)
                  .setArgs(List.of("--no-sandbox"))
      );
   }

   @Override
   public void beforeEach(ExtensionContext context) {
      browserContext = browser.newContext(
            new Browser.NewContextOptions()
                  .setViewportSize(1920, 1080)
      );

      page = browserContext.newPage();

      Guice.createInjector(
            new GuiceModule(page),
            new GuicePagesModule(page)
      ).injectMembers(context.getRequiredTestInstance());
   }

   @Override
   public void afterEach(ExtensionContext context) {
      if (page != null) {
         page.close();
      }
      if (browserContext != null) {
         browserContext.close();
      }
   }

   @Override
   public void afterAll(ExtensionContext context) {
      if (browser != null) {
         browser.close();
      }
      if (playwright != null) {
         playwright.close();
      }
   }
}