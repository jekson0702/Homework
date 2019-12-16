package junitcucumber;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessagePage {
    private static final int TIMEOUT = 15;

    @FindBy(xpath = "//span[contains(text(),'Написать письмо')]")
    private WebElement writeMessageButton;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div" +
            "/div[1]/div/div[2]/div/div/label/div/div/input")
    private WebElement emailInput;

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendMessageButton;

    @FindBy(xpath = "/html/body/div[9]/div/div/div[2]/div[2]/div/div/div[2]/span")
    private WebElement sendAlert;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]" +
            "/div/div/div[2]/div[1]/div[1]") //Работает стабильно только с полным xpath-ом
    private WebElement messageText;

    public SendMessagePage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void clickWriteMessageButton(WebDriver webDriver) {
        expectClickableAndClick(webDriver, writeMessageButton);
    }

    public void inputEmails(WebDriver webDriver, String emeils) {
        expectClickable(webDriver, emailInput);
        emailInput.sendKeys(emeils);
    }

    public void inputMessageText(String text) {
        messageText.sendKeys(text);
    }

    public void sendMessage() {
        sendMessageButton.click();
    }

    public boolean sendMessageAlertIsPresent(WebDriver webDriver) {
        return expectVisibilityAndCheck(webDriver, sendAlert);
    }

    public boolean expectVisibilityAndCheck(WebDriver webDriver, WebElement webElement) {
        return (new WebDriverWait(webDriver, TIMEOUT)).
                until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void expectClickable(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void expectClickableAndClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
        executor.executeScript("arguments[0].click()", webElement);
    }
}