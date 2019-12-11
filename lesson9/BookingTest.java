import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.testng.Assert.*;

public class BookingTest {

    @Test
    public void testSearchByLowestPrice() throws ParseException, InterruptedException {
        Booking booking = new Booking();
        Assert.assertTrue(booking.searchByLowestPrice());
    }
}