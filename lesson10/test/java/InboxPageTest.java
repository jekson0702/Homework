import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InboxPageTest {

    private WebDriver driver;
    private InboxPage inboxPage;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        inboxPage = new InboxPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.enterLoginAndPass("automationqa07", "automation07klin", driver);
    }

    @Test
    public void firstMessageToSpamTest() {
        inboxPage.chooseMessageAndToSpam(driver);
        Assert.assertTrue(inboxPage.toSpamAlertPresents(driver));
    }

    @Test(dependsOnMethods = "firstMessageToSpamTest")
    public void markWithFlagsTest() {
        inboxPage.markWithFlag(driver, 3);
        Assert.assertTrue(inboxPage.flagsIsPresent(driver, 3));
    }

    @Test(dependsOnMethods = "markWithFlagsTest")
    public void demarkWithFlagsTest() {
        inboxPage.deMarkWithFlag(driver);
        Assert.assertTrue(inboxPage.flagsIsNotPresent(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
