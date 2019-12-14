import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendMessagePageTest {

    private WebDriver driver;
    private SendMessagePage sendMessagePage;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        loginPage = new LoginPage(driver);
        sendMessagePage = new SendMessagePage(driver);
        loginPage.enterLoginAndPass("automationqa0702", "jekson07klinrokre1", driver);
    }

    @Test
    public void sendMessageTest() {
        sendMessagePage.sendMessage(driver, "jekson0702@gmail.com, automationqa07@mail.ru", "Hello");
        Assert.assertTrue(sendMessagePage.sendMessageAlert(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
