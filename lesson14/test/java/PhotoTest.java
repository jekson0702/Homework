import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PhotoTest {
    private static final String OWNER_ID = "574645386";
    private static final String TITLE = "New Album";
    private static final String ACCESS_TOKEN =
            "8a971f6d801e7228c111f77756209191d26f744fe78fe6a4a9731d5ecefc7058d296c16dee4af0720c499";
    Photo photo = new Photo();

    @Test
    public void createAlbumTest() {
        photo.createAlbum(ACCESS_TOKEN, TITLE);
        Assert.assertTrue(photo.albumIsPresent(OWNER_ID, ACCESS_TOKEN));
    }

    @Test
    public void deleteAlbumTest() {
        photo.deleteAlbum(ACCESS_TOKEN);
        Assert.assertFalse(photo.albumIsPresent(OWNER_ID, ACCESS_TOKEN));
    }
}