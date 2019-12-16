package junitcucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InboxSteps {
    private InboxPage inboxPage;
    private WebDriver webDriver;
    private LoginSteps loginSteps;

    public InboxSteps() {
        loginSteps = new LoginSteps();
        webDriver = loginSteps.getWebDriver();
        inboxPage = new InboxPage(webDriver);
    }

    @Given("^I am on InboxPage$")
    public void login() {
        loginSteps.loadMainPage();
        loginSteps.loginAsCorrectUser();
    }

    @When("^I choose first message$")
    public void chooseMessage() {
        inboxPage.chooseMessage(webDriver);
    }

    @When("^I click 'to spam'$")
    public void clickToSpam() {
        inboxPage.clickToSpam(webDriver);
    }

    @Then("^I see 'Moved to spam alert'$")
    public void movedToSpamAlertIsPresent() {
        Assert.assertTrue(inboxPage.toSpamAlertPresents(webDriver));
    }

    @When("^I mark three messages with flags$")
    public void markMessagesWithFlags() {
        inboxPage.markWithFlag(webDriver, 3);
    }

    @Then("^I see three marked messages$")
    public void markedMessagesArePresent() {
        Assert.assertTrue(inboxPage.flagsIsPresent(webDriver, 3));
    }

    @When("^i demark all Flags$")
    public void iDemarkAllFlags() {
        inboxPage.deMarkWithFlag(webDriver);
    }

    @Then("^I see no flags$")
    public void iSeeNoFlags() {
        Assert.assertTrue(inboxPage.flagsIsNotPresent(webDriver));
    }

    @After("@messageToSpamFeature")
    public void afterMessageToSpam() {
        webDriver.quit();
    }

    @After("@markMessages")
    public void afterMarkMessages() {
        webDriver.quit();
    }

    @After("@uncheckMessages")
    public void afterClass() {
        webDriver.quit();
    }
}