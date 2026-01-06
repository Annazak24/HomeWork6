//package extensions;
//
//import com.google.inject.AbstractModule;
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//import factory.WebDriverFactory;
//import listeners.HighlightListener;
//import org.junit.jupiter.api.extension.AfterEachCallback;
//import org.junit.jupiter.api.extension.BeforeAllCallback;
//import org.junit.jupiter.api.extension.BeforeEachCallback;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.events.EventFiringDecorator;
//
//public class UiExtensions implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback {
//
//   private WebDriver driver;
//   private final WebDriverFactory webDriverFactory = new WebDriverFactory();
//
//   @Override
//   public void beforeAll(ExtensionContext context) {
//      webDriverFactory.init();
//   }
//
//   @Override
//   public void beforeEach(ExtensionContext context) {
//      WebDriver baseDriver = webDriverFactory.create();
//
//      driver = new EventFiringDecorator<>(new HighlightListener()).decorate(baseDriver);
//
//      Injector injector = Guice.createInjector(
//          new AbstractModule() {
//             @Override
//             protected void configure() {
//                bind(WebDriver.class).toInstance(driver);
//             }
//          },
//          new PagesModule(driver)
//      );
//
//      injector.injectMembers(context.getTestInstance().get());
//   }
//
//   @Override
//   public void afterEach(ExtensionContext context) {
//      if (driver != null) {
//         driver.quit();
//      }
//   }
//}
