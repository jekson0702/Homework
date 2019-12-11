import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;

public class Booking2Test {

    @Test
    public void testSearchByHighestPrice() throws ParseException, InterruptedException {
        Booking2 booking2 = new Booking2();
        Assert.assertTrue(booking2.searchByHighestPrice());
    }
}