package junitcucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final int TIMEOUT = 10;
    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonEnter;

    @FindBy(xpath = "//*[@id='PH_logoutLink']")
    private WebElement logoutLink;

    public LoginPage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void enterLoginAndPass(String login, String password, WebDriver webDriver) {
        loginField.clear();
        loginField.sendKeys(login);
        clickEnterButton();
        expectClickable(webDriver, emailField);
        emailField.clear();
        emailField.sendKeys(password);
        clickEnterButton();
    }

    public void expectClickable(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void clickEnterButton() {
        buttonEnter.click();
    }

    public boolean logoutLinkPresents(WebDriver webDriver) {
        return ((new WebDriverWait(webDriver, TIMEOUT)).until(ExpectedConditions
                .elementToBeClickable(logoutLink)).isDisplayed());
    }
}