import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpamPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private SpamPage spamPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        spamPage = new SpamPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.enterLoginAndPass("automationqa07", "automation07klin", driver);
    }

    @Test
    public void returnFromSpamTest() {
        spamPage.returnFromSpam(driver);
        Assert.assertTrue(spamPage.returnFromSpamAlertPresents(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
