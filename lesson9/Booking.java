import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Booking {
    public boolean searchByLowestPrice() throws ParseException, InterruptedException {
        WebDriver driver = createWebdriver();
        inputCity(driver);
        inputDate(driver);
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='xp__button']"));
        searchButton.click();
        Thread.sleep(5000);
        return sortByPriceAndRating(driver);
    }

    private void inputCity(WebDriver driver) {
        WebElement input = driver.findElement(By.xpath("//input[@type='search']"));
        input.sendKeys("Париж");
    }

    private WebDriver createWebdriver() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");//your own path
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
        return driver;
    }

    public boolean sortByPriceAndRating(WebDriver driver) throws InterruptedException {
        WebElement cheapestHotels = driver.findElement(By.xpath("//a[@data-id=\"pri-1\"]"));
        String maxPriceElement = cheapestHotels.getAttribute("data-value");
        int maxPriceValue = Integer.parseInt(maxPriceElement);
        cheapestHotels.click();
        Thread.sleep(5000);
        WebElement ratingButton = driver.findElement(
                By.xpath("//a[@data-category=\"review_score_and_price\"]"));
        ratingButton.click();
        Thread.sleep(5000);
        WebElement priceOfBestHotel = driver.findElement(
                By.xpath("(//div[@class=\"bui-price-display__value prco-inline-block-maker-helper\"])[1]"));
        double price = (Double.parseDouble(priceOfBestHotel.getText()
                .substring(4).replace(" ", ""))) / 7;
        return price <= maxPriceValue;
    }

    public void inputDate(WebDriver driver) throws ParseException {
        WebElement dateField = driver.findElement(By.xpath("//div[@class='xp__dates-inner']"));
        dateField.click();
        WebElement today = driver.findElement(
                By.xpath("//td[@class=\"bui-calendar__date bui-calendar__date--today\"]"));
        String atribute = today.getAttribute("data-date");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormat.parse(atribute));
        cal.add(Calendar.DATE, 3);
        String datePlusThree = dateFormat.format(cal.getTime());
        WebElement firstDate = driver.findElement(By.xpath("//td[@data-date='" + datePlusThree + "']"));
        firstDate.click();
        cal.add(Calendar.DATE, 7);
        String datePlusTen = dateFormat.format(cal.getTime());
        WebElement secondDate = driver.findElement(By.xpath("//td[@data-date='" + datePlusTen + "']"));
        secondDate.click();
    }
}
