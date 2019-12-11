import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.testng.Assert.*;

public class Booking3Test {

    @Test
    public void testErrorMessage() throws ParseException, InterruptedException {
        Booking3 booking3 = new Booking3();
        Assert.assertTrue(booking3.searchByHighestPriceAndPay());
    }
}