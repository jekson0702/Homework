import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

public class Booking3 {

    public boolean searchByHighestPriceAndPay() throws ParseException, InterruptedException {
        WebDriver driver = createWebDriver();
        inputCity(driver);
        inputDate(driver);
        inputGuestsAndRooms(driver);
        enterButton(driver);
        sortByPrice(driver);
        return bookAndPayWithError(driver);
    }

    public boolean bookAndPayWithError(WebDriver driver) throws InterruptedException {
        WebElement chooseHotelButton = driver.findElement(
                By.xpath("(//div[@class=\"sr-cta-button-row\"])[1]"));
        chooseHotelButton.click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(5000);
        WebElement chooseRoom = driver.findElement(
                By.xpath("//a[contains(@class,\"b-button b-button_primary \")]"));
        chooseRoom.click();
        Thread.sleep(5000);
        WebElement confirm = driver.findElement(
                By.xpath("//button[contains(@class,\"b-button b-button_primary \") and @type=\"submit\" " +
                        "and @data-tooltip-class=\"submit_holder_button_tooltip\"]"));
        confirm.click();
        Thread.sleep(5000);
        WebElement one = driver.findElement(By.xpath("//input[@id=\"bp_travel_purpose_business\"]"));
        one.click();
        Select selectMr = new Select(driver.findElement(By.xpath("//select[@name=\"booker_title\"]")));
        selectMr.selectByValue("mr");
        WebElement name = driver.findElement(By.xpath("//input[@name=\"firstname\"]"));
        name.sendKeys("gjhasgfhasgfj");
        WebElement surname = driver.findElement(By.xpath("//input[@name=\"lastname\"]"));
        surname.sendKeys("gjhasgfhasgfj");
        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("gjhasgfhasgfj@mail.ru");
        WebElement emailConfirm = driver.findElement(By.xpath("//input[@name=\"email_confirm\"]"));
        emailConfirm.sendKeys("gjhasgfhasgfj@mail.ru");
        WebElement guest = driver.findElement(By.xpath("//input[@id=\"notstayer_false\"]"));
        guest.click();
        Thread.sleep(1500);
        WebElement submitButton = driver.findElement(By.xpath("//button[@type=\"submit\" and @name=\"book\"]"));
        submitButton.click();
        WebElement inputAdress = driver.findElement(By.xpath("//input[@name=\"address1\"]"));
        inputAdress.sendKeys("asdasd");
        WebElement city = driver.findElement(By.xpath("//input[@name=\"city\"]"));
        city.sendKeys("aasfasf");
        WebElement phone = driver.findElement(By.xpath("//input[@name=\"phone\"]"));
        phone.sendKeys("+375664215488");
        WebElement payNow = driver.findElement(By.xpath("//input[@value=\"pay-now\"]"));
        payNow.click();
        Select selectCard = new Select(driver.findElement(By.xpath("//select[@name=\"cc_type\"]")));
        selectCard.selectByValue("Visa");
        Select selectCardEnd = new Select(driver.findElement(By.xpath("//select[@id=\"ccYear\"]")));
        selectCardEnd.selectByValue("2021");
        WebElement cardNumber = driver.findElement(By.xpath("//input[@name=\"cc_number\"]"));
        cardNumber.sendKeys("4242424242424242");
        WebElement cvv = driver.findElement(By.xpath("//input[@name=\"cc_cvc\"]"));
        cvv.sendKeys("888");
        Thread.sleep(2000);
        WebElement confirmPayButton = driver.findElement(
                By.xpath("(//button[@type=\"submit\" and @name=\"book\"])[2]"));
        confirmPayButton.click();
        Thread.sleep(15000);
        return (driver.findElement(By.xpath("//*[@id=\"bookStage3Inc\"]/div[2]"))).isDisplayed();
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

    public void sortByPrice(WebDriver driver) throws InterruptedException {
        WebElement expensiveHotels = driver.findElement(By.xpath("//a[@data-id=\"pri-5\"]"));
        expensiveHotels.click();
        Thread.sleep(5000);
        WebElement priceButton = driver.findElement(
                By.xpath("//a[@data-category=\"price\"]"));
        priceButton.click();
        Thread.sleep(5000);
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
