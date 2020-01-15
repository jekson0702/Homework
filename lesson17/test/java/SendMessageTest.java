import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendMessageTest {
    private WebDriver driver;
    private SendMessage sendMessage;
    private MailData mailData;

    @BeforeClass
    public void createDriver() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.ru/");
        mailData = new MailData();
        mailData.setMailDataFromDataBase();
        sendMessage = new SendMessage(driver);
    }

    @Test
    public void sendMessageTest() {
        sendMessage.enterLogin(mailData.getLogin());
        sendMessage.enterPassword(mailData.getPassword(), driver);
        sendMessage.enterAddresses(mailData.getAddresses(), driver);
        sendMessage.enterMessageText(mailData.getMessageText());
        sendMessage.sendMessage();
        Assert.assertTrue(sendMessage.sendMessageAlertIsPresent(driver));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}