import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.Eyes;
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

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendMessageButton;

    @FindBy(xpath = "/html/body/div[9]/div/div/div[2]/div[2]/div/div/div[2]/span")
    private WebElement sendAlert;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]")
    private WebElement staleElement;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]" +
            "/div/div/div[2]/div[1]/div[1]") //Работает стабильно только с полным xpath-ом
    private WebElement messageText;

    private static final String LOGIN = "automationqa07";
    private static final String PASSWORD = "qaautomation";

    public SendMessage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void send(WebDriver driver, Eyes eyes) {
        driver.get("https://mail.ru/");
        eyes.checkWindow("LoginPageScreen");
        enterLogin(LOGIN);
        eyes.checkWindow("EnterLoginScreen");
        clickEnterButton();
        enterPassword(PASSWORD, driver);
        eyes.checkWindow("EnterPasswordScreen");
        clickEnterButton();
        expectVisibility(driver, writeMessageButton);
        eyes.checkWindow("InboxPageScreen");
        expectClickableAndClick(driver, writeMessageButton);
        expectClickableAndClick(driver, messageText);
        eyes.checkWindow("WriteMessageWindowScreen");
        emailInput.sendKeys("jekson0702@gmail.com, automationqa0702@mail.ru");
        messageText.sendKeys("Hello");
        eyes.checkWindow("MessageBeforeSending");
        sendMessageButton.click();
        expectVisibility(driver, sendAlert);
        eyes.checkWindow("SendMessageAlertScreen");
    }

    public boolean testPassed(EyesRunner runner) {
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults.getAllResults()[0].getTestResults().isPassed();
    }

    public void expectVisibility(WebDriver webDriver, WebElement webElement) {
        (new WebDriverWait(webDriver, TIMEOUT)).
                until(ExpectedConditions.visibilityOf(webElement));
    }

    public void expectClickableAndClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
        executor.executeScript("arguments[0].click()", webElement);
    }

    public void enterLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void enterPassword(String password, WebDriver driver) {
        expectClickable(driver, passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void expectClickable(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void clickEnterButton() {
        buttonEnter.click();
    }
}