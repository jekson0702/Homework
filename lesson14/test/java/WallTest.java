import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class WallTest {
    private static final String OWNER_ID = "574645386";
    private static final String MESSAGE = "HELLO";
    private static final String EDITED_MESSAGE = "EDITED";
    private static final String ACCESS_TOKEN =
            "8d4f98a6b12c5ebfaaef202e7d07ed773296bb9522ab88abaf143a8a3291cc8f729af1eb6df7a0cbd6f54";
    Wall wall = new Wall();

    @Test()
    public void postMessageTest() {
        wall.postMessage(MESSAGE, OWNER_ID, ACCESS_TOKEN);
        Assert.assertTrue(wall.postIsPresent(OWNER_ID, ACCESS_TOKEN));
    }

    @Test(priority = 1)
    public void editMessageTest() {
        wall.editMessage(EDITED_MESSAGE, OWNER_ID, ACCESS_TOKEN);
        Assert.assertTrue(wall.postIsEdited(EDITED_MESSAGE, OWNER_ID, ACCESS_TOKEN));
    }

    @Test(priority = 2)
    public void deleteMessageTest() {
        wall.deleteMessage(OWNER_ID, ACCESS_TOKEN);
        Assert.assertFalse(wall.postIsPresent(OWNER_ID, ACCESS_TOKEN));
    }
}