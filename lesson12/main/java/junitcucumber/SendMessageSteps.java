package junitcucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SendMessageSteps {
    private static final String EMAILS = "jekson0702@gmail.com, automationqa0702@mail.ru";
    private static final String MESSAGE_TEXT = "Hello";
    private SendMessagePage sendMessagePage;
    private WebDriver webDriver;
    private LoginSteps loginSteps;

    public SendMessageSteps() {
        loginSteps = new LoginSteps();
        webDriver = loginSteps.getWebDriver();
        sendMessagePage = new SendMessagePage(webDriver);
    }

    @Given("^I am on Inbox Page$")
    public void login() {
        loginSteps.loadMainPage();
        loginSteps.loginAsCorrectUser();
    }

    @When("^I click button 'Написать сообщение'$")
    public void clickWriteMessage() {
        sendMessagePage.clickWriteMessageButton(webDriver);
    }

    @When("^I input emails$")
    public void inputEmails() {
        sendMessagePage.inputEmails(webDriver, EMAILS);
    }

    @When("^I input message text$")
    public void inputMessageText() {
        sendMessagePage.inputMessageText(MESSAGE_TEXT);
    }

    @When("^I click 'Отправить сообщение'$")
    public void clickSendMessage() {
        sendMessagePage.sendMessage();
    }

    @Then("^I see 'Письмо отправлено'$")
    public void sendMessageAlert() {
        Assert.assertTrue(sendMessagePage.sendMessageAlertIsPresent(webDriver));
    }

    @After("@sendMessageFeature")
    public void afterClass() {
        webDriver.quit();
    }
}
