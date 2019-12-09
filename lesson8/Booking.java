import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Booking {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = getWebDriver();
        inputFields(driver);
        getAvailableHotels(driver);
        getRatingOfFirstHotel(driver);
    }

    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");//your own path
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
        return driver;
    }

    public static void inputFields(WebDriver driver) {
        WebElement input = driver.findElement(By.xpath("//input[@type='search']"));
        input.sendKeys("Москва");
        WebElement data = driver.findElement(By.xpath("//div[@class='xp__dates-inner']"));
        data.click();
        WebElement firstData = driver.findElement(
                By.xpath("//td[@data-date='2019-12-25']"));
        firstData.click();
        WebElement secondData = driver.findElement(
                By.xpath("//td[@data-date='2019-12-28']"));
        secondData.click();
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='xp__button']"));
        searchButton.click();
    }

    public static void getRatingOfFirstHotel(WebDriver driver) throws InterruptedException {
        WebElement rating = driver.findElement(
                By.xpath("//a[@data-id='review_score-90' and @class=' filterelement        ']"));
        rating.click();
        Thread.sleep(2000);
        WebElement firstHotelRating = driver.findElement(By.xpath("//div[@class='bui-review-score__badge']"));
        int ratingValue = Integer.parseInt(firstHotelRating.getText().substring(0, 1));
        System.out.println(ratingValue < 9 ? "Rating of the first hotel is less than 9" :
                "Rating of the first hotel is 9+");
    }

    public static void getAvailableHotels(WebDriver driver) {
        List<WebElement> listOfHotels = driver.findElements(
                By.xpath("//div[@class='sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout" +
                        "                 ']"));
        System.out.println(listOfHotels.isEmpty() ? "Hotels aren't available" : "Hotels are available");
    }
}
