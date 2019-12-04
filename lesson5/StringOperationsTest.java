import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class StringOperationsTest {
    @DataProvider
    public Object[][] dataProviderTestGetSumUpperCase() {
        return new Object[][]{{"aa", "bb", "AABB"}};
    }

    @DataProvider
    public Object[][] dataProviderTestGetLength() {
        return new Object[][]{{"abc", 3}, {"", 0}, {"  dff  ", 7}};
    }

    @DataProvider
    public Object[][] dataProviderTestGetSum() {
        return new Object[][]{{"aa", "bb", "aabb"}, {"", "", ""}};

    }

    @Test
    public void testGetSum() {
        StringOperations stringOperations = new StringOperations();
        int expected = 5;
        int actual = stringOperations.getSum(2, 3);
        Assert.assertEquals(actual, expected);
    }

    @Parameters({"param1"})
    @Test
    public void testGetSqrt(int param1) {
        StringOperations stringOperations = new StringOperations();
        double actual = stringOperations.getSqrt(param1);
        double expected = 2;
        Assert.assertEquals(actual, expected);

    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testGetDivision() {
        StringOperations stringOperations = new StringOperations();
        int actual = stringOperations.getDivision(2, 0);

    }

    @Test(enabled = false)
    public void testGetAverage() {
        StringOperations stringOperations = new StringOperations();
        double actual = stringOperations.getAverage(2, 4);
        double expected = 3;
        Assert.assertEquals(actual, expected);

    }

    @Test()
    public void testGetAverage1() {
        StringOperations stringOperations = new StringOperations();
        double actual = stringOperations.getAverage(2, 4);
        double expected = 3;
        Assert.assertEquals(actual, expected);

    }

    @Test(dataProvider = "dataProviderTestGetSumUpperCase")
    public void testGetSumUpperCase(String line1, String line2, String expected) {
        StringOperations stringOperations = new StringOperations();
        String actual = stringOperations.getSumUpperCase(line1, line2);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataProviderTestGetSum")
    public void testGetSum(String line1, String line2, String expected) {
        StringOperations stringOperations = new StringOperations();
        String actual = stringOperations.getSum(line1, line2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetReverseSum() {
        StringOperations stringOperations = new StringOperations();
        String actual = stringOperations.getReverseSum("abc", "dfasf", "asda");
        String expected = "asdadfasfabc";
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataProviderTestGetLength")
    public void testGetLength(String str, int expected) {
        StringOperations stringOperations = new StringOperations();
        int actual = stringOperations.getLength(str);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetReverseSumUpperCase() {
        StringOperations stringOperations = new StringOperations();
        String actual = stringOperations.getReverseSumUpperCase("aa", "bb");
        String expected = "BBAA";
        Assert.assertEquals(actual, expected);
    }
}