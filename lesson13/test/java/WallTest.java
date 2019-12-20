import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class WallTest {
    private static final String OWNER_ID = "574645386";
    private static final String MESSAGE = "HELLO";
    private static final String EDITED_MESSAGE = "EDITED";
    private static final String ACCESS_TOKEN =
            "22405f282b66c7f23fa84d21a1bf474211a59c2cab7c901b16db9582894f4d5f2d0094669f58859f40acb";
    Wall wall = new Wall();

    @Test()
    public void postMessageTest() throws IOException, URISyntaxException {
        wall.postMessage(MESSAGE, OWNER_ID, ACCESS_TOKEN);
        Assert.assertTrue(wall.postIsPresent(OWNER_ID, ACCESS_TOKEN));
    }

    @Test(priority = 1)
    public void editMessageTest() throws IOException, URISyntaxException {
        wall.editMessage(EDITED_MESSAGE, OWNER_ID, ACCESS_TOKEN);
        Assert.assertTrue(wall.postIsEdited(EDITED_MESSAGE, OWNER_ID, ACCESS_TOKEN));
    }

    @Test(priority = 2)
    public void deleteMessageTest() throws IOException, URISyntaxException {
        wall.deleteMessage(OWNER_ID, ACCESS_TOKEN);
        Assert.assertFalse(wall.postIsPresent(OWNER_ID, ACCESS_TOKEN));
    }
}