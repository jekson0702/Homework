import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SendMessageTest {
    private EyesRunner runner;
    private Eyes eyes;
    private WebDriver driver;
    private SendMessage sendMessage;
    private static final String API_KEY = "apcSt2103NsuOFPkuGKVfv0etCrptpjHIH3ue4yCbtCbU110";

    @BeforeClass
    public void eyesOpen() {
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(API_KEY);
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
        sendMessage = new SendMessage(driver);
        eyes.open(driver, "MailRu", "SendMessage", new RectangleSize(1400, 600));
        eyes.setMatchLevel(MatchLevel.CONTENT);
    }

    @Test
    public void sendMessageTest() {
        sendMessage.send(driver, eyes);
        eyes.closeAsync();
        driver.quit();
        eyes.abortIfNotClosed();
        Assert.assertTrue(sendMessage.testPassed(runner));
    }
}