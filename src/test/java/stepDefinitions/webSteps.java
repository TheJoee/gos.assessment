package stepDefinitions;

import com.gospace.assesment.factories.DriverFactory;
import com.gospace.assesment.pages.*;
import com.gospace.assesment.utils.ConfigReader;
import com.gospace.assesment.utils.HelperMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

public class webSteps {

    private static final String url = ConfigReader.getProperty("BASE_URL");
    private static final String uniqueId = ((new Date().getTime() / 1000L) % Integer.MAX_VALUE)+"";
    private static final String repoName = "TestRepo-" + uniqueId;
    private final WebDriver driver = DriverFactory.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final TwoFactorPage twoFactorPage = new TwoFactorPage(driver);
    private final DashboardPage dashboardPage = new DashboardPage(driver);
    private final UserProfilePage userProfilePage = new UserProfilePage(driver);
    private final NewRepositoryPage newRepositoryPage = new NewRepositoryPage(driver);

    public webSteps() throws IOException {
    }

    @Given("I navigate to Github login page")
    public void i_navigate_to_github_login_page() {
        driver.get(url + "login");
        Assertions.assertTrue(loginPage.onLoginPage(), "Page not loaded correctly");
    }
    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() throws InterruptedException, URISyntaxException {
        String username = ConfigReader.getProperty("USERNAME");
        String password = ConfigReader.getProperty("PASSWORD").replace("'","");
        loginPage.enterLoginInfo(username,password);
        loginPage.pressLoginButton();
        twoFactorPage.enterOTPIfNeeded();
    }
    @Then("I will see landing page")
    public void i_will_see_landing_page() {
        Assert.assertTrue("Landing page is not displayed", dashboardPage.onDashboardPage(driver));
    }

    @When("I navigate to repositories page")
    public void i_navigate_to_repositories_pager() {
        driver.get(url + ConfigReader.getProperty("USERNAME") + "?tab=repositories");
        HelperMethods.waitForPageToBeReady(driver);
    }

    @Then("I will see all the users repositories list")
    public void i_will_see_all_the_users_repositories_list() {
        Assert.assertTrue(userProfilePage.onRepositoriesTab(driver));
    }

    @When("I select option to create a new repository")
    public void i_select_option_to_create_a_new_repository() {
        userProfilePage.createNewRepository(driver);
    }
    @Then("I will see new repository page")
    public void i_will_see_new_repository_page() {
        newRepositoryPage.onNewRepositoryPage(driver);
    }
    @When("I fill all required information and confirm")
    public void i_fill_all_required_information_and_confirm() {
        newRepositoryPage.fillNewRepositoryFields(driver, repoName);
        newRepositoryPage.submitNewRepositoryForm(driver);
    }
    @Then("I will see the created repository on the repositories list")
    public void i_will_see_the_created_repository_on_the_repositories_list() {
        driver.get(url + ConfigReader.getProperty("USERNAME") + "?tab=repositories");
        HelperMethods.waitForPageToBeReady(driver);
        userProfilePage.onRepositoriesTab(driver);
        Assert.assertTrue("Repository Missing!", userProfilePage.doesRepositoryExist(driver, repoName));
    }

    @Then("I will close the browser")
    public void i_will_close_the_browser() {
        driver.quit();
    }
}
