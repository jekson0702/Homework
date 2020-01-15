import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessage {
    private static final int TIMEOUT = 10;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonEnter;

    @FindBy(xpath = "//span[contains(text(),'Написать письмо')]")
    private WebElement writeMessageButton;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div" +
            "/div[1]/div/div[2]/div/div/label/div/div/input")
    private WebElement emailInput;

    @FindBy(xpath = "//span[@title='Отправить']")
    private WebElement sendMessageButton;

    @FindBy(xpath = "/html/body/div[9]/div/div/div[2]/div[2]/div/div/div[2]/span")
    private WebElement sendAlert;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]")
    private WebElement staleElement;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]" +
            "/div/div/div[2]/div[1]/div[1]")
    private WebElement messageTextField;

    public SendMessage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void enterLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
        clickEnterButton();
    }

    public void enterPassword(String password, WebDriver driver) {
        expectClickable(driver, passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        clickEnterButton();
    }

    public void enterAddresses(String addresses, WebDriver driver) {
        expectClickableAndClick(driver, writeMessageButton);
        expectClickable(driver, emailInput);
        emailInput.sendKeys(addresses);
    }

    public void enterMessageText(String messageText) {
        messageTextField.sendKeys(messageText);
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

    public void expectClickableAndClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
        executor.executeScript("arguments[0].click()", webElement);
    }

    public void expectClickable(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void clickEnterButton() {
        buttonEnter.click();
    }
}