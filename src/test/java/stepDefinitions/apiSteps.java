package stepDefinitions;

import com.gospace.assesment.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class apiSteps {

    private final String configFile = "env.conf";
    private final String organization = ConfigReader.getProperty("ORG_NAME");
    private final String url = ConfigReader.getProperty("API_BASE_URL");
    private final String token = ConfigReader.getProperty("TOKEN");
    private final String apiVersion = ConfigReader.getProperty("API_VERSION");
    private final String uniqueId = ((new Date().getTime() / 1000L) % Integer.MAX_VALUE)+"";
    private Response response;
    private final String acceptObject = "Accept: application/vnd.github+json";
    private final String authorizationObject = "Bearer " + token;
    private final String orgReposEndpoint = "orgs/"+organization+"/repos";
    private final String specificOrgRepoEndpoint = "repos/"+organization+"/Repo_"+uniqueId;

    public apiSteps() {
    }

    @Given("The list of repositories is available")
    public void the_list_of_repositories_is_available() {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Accept", acceptObject)
                .header("Authorization",authorizationObject )
                .header("X-GitHub-Api-Version", apiVersion);
        response = request.get(orgReposEndpoint);
        Assert.assertEquals(200, response.getStatusCode());
        String jsonString = response.asString();
        List<Map<String, String>> repos = JsonPath.from(jsonString).get("name");
        System.out.println(repos);
    }
    @When("I create a new repository")
    public void i_create_a_new_repository() {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Accept", acceptObject)
                .header("Authorization",authorizationObject )
                .header("X-GitHub-Api-Version", apiVersion);
        response = request.body("{ \"name\": \"" + "Repo_"+uniqueId + "\" }").post(orgReposEndpoint);
        Assert.assertEquals(201, response.getStatusCode());
    }
    @Then("the repository is now on the list")
    public void the_repository_is_now_on_the_list() {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Accept", acceptObject)
                .header("Authorization",authorizationObject )
                .header("X-GitHub-Api-Version", apiVersion);
        response = request.get(orgReposEndpoint);
        Assert.assertEquals(200, response.getStatusCode());
        String jsonString = response.asString();
        List<Map<String, String>> repos = JsonPath.from(jsonString).get("name");
        assertTrue(repos.contains("Repo_"+uniqueId),"Repo is not on the list");
    }
    @When("I delete a repository")
    public void i_delete_a_repository() {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Accept", acceptObject)
                .header("Authorization",authorizationObject )
                .header("X-GitHub-Api-Version", apiVersion);
        response = request.delete(specificOrgRepoEndpoint);
        Assert.assertEquals(204, response.getStatusCode());
    }
    @Then("the repository is no longer on the list")
    public void the_repository_is_no_longer_on_the_list() {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Accept", acceptObject)
                .header("Authorization",authorizationObject )
                .header("X-GitHub-Api-Version", apiVersion);
        response = request.get(orgReposEndpoint);
        Assert.assertEquals(200, response.getStatusCode());
        String jsonString = response.asString();
        List<Map<String, String>> repos = JsonPath.from(jsonString).get("name");
        assertFalse(repos.contains("Repo_"+uniqueId),"Repo is still on the list");
    }
}
