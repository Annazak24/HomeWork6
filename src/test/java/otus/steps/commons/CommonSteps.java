package otus.steps.commons;

import io.cucumber.java.en.Given;
import jakarta.inject.Inject;
import support.GuiceScoped;

public class CommonSteps {

    @Inject
    private GuiceScoped guiceScoped;

    private String baseUrl = System.getProperty("base.url");

    @Given("I open browser Chrome")
    public void openBrowser() {
        guiceScoped.getDriver().get(baseUrl);
    }
}
