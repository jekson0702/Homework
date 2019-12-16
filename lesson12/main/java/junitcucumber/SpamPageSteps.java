package junitcucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SpamPageSteps {
    private SpamPage spamPage;
    private WebDriver webDriver;
    private LoginSteps loginSteps;

    public SpamPageSteps() {
        loginSteps = new LoginSteps();
        webDriver = loginSteps.getWebDriver();
        spamPage = new SpamPage(webDriver);
    }

    @Given("^I am on Spam Page$")
    public void iAmOnSpamPage() {
        loginSteps.loadMainPage();
        loginSteps.loginAsCorrectUser();
        spamPage.goToSpamPage(webDriver);
    }

    @When("^I choose first message in Spam$")
    public void iChooseFirstMessage() {
        spamPage.chooseFirstMessage(webDriver);
    }

    @When("^I click 'Не спам'$")
    public void iClickNotSpam() {
        spamPage.returnFromSpam(webDriver);
    }

    @Then("^I see 'Письмо перемещено в папку Входящие'\\.$")
    public void returnFromSpamAlertIsPresent() {
        Assert.assertTrue(spamPage.returnFromSpamAlertPresents(webDriver));
    }

    @After("@returnFromSpamFeature")
    public void afterClass() {
        webDriver.quit();
    }
}