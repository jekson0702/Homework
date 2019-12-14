import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginTest() {
        loginPage.enterLoginAndPass("automationqa07", "automation07klin", driver);
        Assert.assertTrue(loginPage.logoutLinkPresents(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}