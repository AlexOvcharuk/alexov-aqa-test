package starter.stepdefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.hamcrest.Matchers;

import java.net.URISyntaxException;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class SearchStepDefinitions {

    @BeforeAll
    public static void beforeAll() throws URISyntaxException {
        // Init BaseURI
        EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
        RestAssured.baseURI = EnvironmentSpecificConfiguration.from(env).getProperty("restapi.baseurl");
    }


    @When("he searches for {word}")
    public void heSearches(String product) {
        SerenityRest.given().get(product);
    }

    @Then("he sees the results displayed for {word}")
    public void heSeesTheResultsDisplayedFor(String product) {
        restAssuredThat(response-> response.statusCode(200));
        restAssuredThat(response -> response.body("size()", greaterThan(0)));
    }

    @Then("he does not see the results")
    public void heDoesNoSeeTheResults() {
        then().statusCode(404).body("error", Matchers.anyOf(equalTo(true), equalTo(null)));
    }
}
