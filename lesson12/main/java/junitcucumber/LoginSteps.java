package junitcucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "automationqa07";
    private static final String PASSWORD = "qaautomation";
    private LoginPage loginPage;
    private WebDriver webDriver;

    public LoginSteps() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Given("^I am on main application page$")
    public void loadMainPage() {
        webDriver.get(MAIN_URL);
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser() {
        loginPage.enterLoginAndPass(LOGIN, PASSWORD, webDriver);
    }

    @Then("^I see logout link$")
    public void seeLogoutLink() {
        Assert.assertTrue(loginPage.logoutLinkPresents(webDriver));
    }

    @After("@loginFeature")
    public void afterClass() {
        webDriver.quit();
    }
}