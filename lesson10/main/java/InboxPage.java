import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InboxPage {
    private static final int TIMEOUT = 15;

    @FindBy(xpath = "(//div[@class=\"ll-av__container\"])[1]")
    private WebElement firstMessageCheckbox;

    @FindBy(xpath = "//span[@title=\"Спам\"]")
    private WebElement toSpamButton;

    @FindBy(xpath = "//*[contains(text(),'Перемещено в спам')]")
    private WebElement spamAlert;

    @FindBy(xpath = "(//button[@title=\"Пометить флажком\"])[1]")
    private WebElement flag;

    @FindBy(xpath = "//button[@title=\"Пометить флажком\"]")
    private List<WebElement> flags;

    @FindBy(xpath = "//button[@title=\"Снять флажок\"]")
    private WebElement deflag;

    @FindBy(xpath = "//button[@data-title=\"Снять флажок\" or @title=\"Снять флажок\"]")
    private List<WebElement> flagedList;

    @FindBy(xpath = "//a[@href=\"/inbox/\"]")
    private WebElement inbox;

    public InboxPage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void chooseMessageAndToSpam(WebDriver webDriver) {
        expectClickableAndClick(webDriver, firstMessageCheckbox);
        expectClickableAndClick(webDriver, toSpamButton);
    }

    public void markWithFlag(WebDriver webDriver, int numberOfMessages) {
        do {
            expectClickableAndClick(webDriver, flag);
            expectVisibilityOfAllElements(webDriver, flagedList);
        }
        while (flagedList.size() < numberOfMessages);
    }

    public void deMarkWithFlag(WebDriver webDriver) {
        do {
            expectVisibilityOfAllElements(webDriver, flagedList);
            expectClickableAndClick(webDriver, deflag);
            expectVisibilityOfAllElements(webDriver,flags);
        }
        while (flagedList.size() > 0);
    }

    public boolean toSpamAlertPresents(WebDriver webDriver) {
        return expectVisibilityAndCheck(webDriver, spamAlert);
    }

    public boolean flagsIsPresent(WebDriver webDriver, int numberOfFlags) {
        expectVisibilityOfAllElements(webDriver, flagedList);
        return (flagedList.size() == numberOfFlags);
    }

    public boolean flagsIsNotPresent(WebDriver webDriver) {
        expectVisibilityOfAllElements(webDriver, flags);
        return flagedList.isEmpty();
    }

    public boolean expectVisibilityAndCheck(WebDriver webDriver, WebElement webElement) {
        return (new WebDriverWait(webDriver, TIMEOUT)).
                until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void expectVisibilityOfAllElements(WebDriver webDriver, List<WebElement> list) {
        (new WebDriverWait(webDriver, TIMEOUT)).
                until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public void expectClickableAndClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
        executor.executeScript("arguments[0].click()", webElement);
    }
}
