import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpamPage {
    private static final int TIMEOUT = 15;

    @FindBy(xpath = "(//div[@class=\"ll-av__container\"])[1]")
    private WebElement firstMessageCheckbox;

    @FindBy(xpath = "//a[@href=\"/spam/\"]")
    private WebElement spamButton;

    @FindBy(xpath = "//span[@data-title-shortcut=\"Shift+J\"]")
    private WebElement notSpamButton;

    @FindBy(xpath = "//*[@id=\"app-canvas\"]/div/div[1]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div[2]/a")
    private WebElement clearFolderButton;

    @FindBy(xpath = "//*[contains(text(),'Перемещено в папку')]")
    private WebElement toAnotherFolderAlert;

    public SpamPage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void returnFromSpam(WebDriver webDriver) {
        expectClickableAndClick(webDriver, spamButton);
        expectVisibility(webDriver, clearFolderButton);
        expectClickableAndClick(webDriver, firstMessageCheckbox);
        expectClickableAndClick(webDriver, notSpamButton);
    }

    public boolean returnFromSpamAlertPresents(WebDriver webDriver) {
        return expectVisibilityAndCheck(webDriver, toAnotherFolderAlert);
    }

    public void expectClickableAndClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
        executor.executeScript("arguments[0].click()", webElement);
    }

    public boolean expectVisibilityAndCheck(WebDriver webDriver, WebElement webElement) {
        return (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void expectVisibility(WebDriver webDriver, WebElement webElement) {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
