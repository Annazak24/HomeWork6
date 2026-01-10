package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CatalogPage;
import support.GuiceScoped;

public class CatalogPageSteps {

    @Inject
    private CatalogPage catalogPage;
    @Inject
    private GuiceScoped guiceScoped;

    @And("Courses catalog page is opened")
    public void setCatalogPage() {
        catalogPage.openCatalogPage();
    }

    @When("Click on a random course")
    public void clickRandomCourse() {
        String courseName = catalogPage.getCourseTitleByJsoup();
        catalogPage.clickCourseByName(courseName);
        guiceScoped.store(courseName, "courseName");
    }

    @When("I search for courses starting on or after 01.09.2025")
    public void searchCourses() {
        catalogPage.searchCoursesStartingFromDate("01.09.2025");
    }

    @Then("I print courses information to console")
    public void printCourses() {
        catalogPage.printCoursesInfoToConsole();
    }

    @When("I open Preparatory courses section")
    public void openPreparatoryCourses()  {
        catalogPage.clickPreparatoryCoursesLabel();
    }

    @And("I find the cheapest and the most expensive course")
    public void findExpensiveAndCheapCourses(){
        catalogPage.findCheapestAndMostExpensiveCourses();
    }

    @Then("I print selected courses information to console")
    public void printCoursesPrice(){
        catalogPage.printSelectedCoursesInformation();
    }
}
