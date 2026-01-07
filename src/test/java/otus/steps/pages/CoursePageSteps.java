package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CatalogPage;
import support.GuiceScoped;

import static org.assertj.core.api.Assertions.assertThat;

public class

CoursePageSteps {

    @Inject
    private GuiceScoped guiceScoped;

    @Inject
    private CatalogPage catalogPage;

    @Then("Course page is opened successfully")
    public void shouldBeOpenedCoursePage(){
        String courseName= guiceScoped.retriver("courseName");
        assertThat(courseName = catalogPage.getCourseTitleByJsoup());
    }

    @And("I find the cheapest and the most expensive course")
    public void findExpensiveAndCheapCourses(){
        catalogPage.collectPrices();
    }
}
