import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Booking2 {

    public boolean searchByHighestPrice() throws ParseException, InterruptedException {
        WebDriver driver = createWebDriver();
        inputCity(driver);
        inputDate(driver);
        inputGuestsAndRooms(driver);
        enterButton(driver);
        return sortByPrice(driver);
    }

    public void enterButton(WebDriver driver) throws InterruptedException {
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='xp__button']"));
        searchButton.click();
        Thread.sleep(5000);
    }

    public WebDriver createWebDriver() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");//your own path
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
        return driver;
    }

    public boolean sortByPrice(WebDriver driver) throws InterruptedException {
        WebElement expensiveHotels = driver.findElement(By.xpath("//a[@data-id=\"pri-5\"]"));
        WebElement maxPriceElement = driver.findElement(By.xpath("//a[@data-id=\"pri-4\"]"));
        String maxPriceValue = maxPriceElement.getAttribute("data-value");
        int minPrice = Integer.parseInt(maxPriceValue);
        expensiveHotels.click();
        Thread.sleep(5000);
        WebElement priceButton = driver.findElement(
                By.xpath("//a[@data-category=\"price\"]"));
        priceButton.click();
        Thread.sleep(5000);
        WebElement priceOfBestHotel = driver.findElement(
                By.xpath("(//div[@class=\"bui-price-display__value prco-inline-block-maker-helper\"])[1]"));
        double price = (Double.parseDouble(priceOfBestHotel.getText()
                .substring(4).replace(" ", ""))) / 7;
        return price >= minPrice;

    }

    public void inputGuestsAndRooms(WebDriver driver) throws InterruptedException {
        WebElement adults = driver.findElement(By.xpath("//input[@name=\"group_adults\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('value', '4')", adults);
        Thread.sleep(1000);
        WebElement numberofRooms = driver.findElement(By.xpath("//input[@name=\"no_rooms\"]"));
        executor.executeScript("arguments[0].setAttribute('value', '2')", numberofRooms);
        Thread.sleep(1000);

    }

    public void inputDate(WebDriver driver) throws ParseException {
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

    public void inputCity(WebDriver driver) {
        WebElement input = driver.findElement(By.xpath("//input[@type='search']"));
        input.sendKeys("Париж");
        WebElement dateField = driver.findElement(By.xpath("//div[@class='xp__dates-inner']"));
        dateField.click();
    }
}
