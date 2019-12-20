import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PhotoTest {
    private static final String OWNER_ID = "574645386";
    private static final String TITLE = "New Album";
    private static final String ACCESS_TOKEN =
            "efa6d2cb40af5bad71ffcb21864df2eb6587ba4f90eb0e2ded3813d886ffe0349d22865ca3d141bc22c4c";
    Photo photo = new Photo();

//загрузку фото не реализовал, только создание альбома для загрузки, получение URL для загрузки и удаление альбома

    @Test
    public void createAlbumTest() throws IOException, URISyntaxException {
        photo.createAlbum(ACCESS_TOKEN, TITLE);
        Assert.assertTrue(photo.albumIsPresent(OWNER_ID, ACCESS_TOKEN));
    }

    @Test
    public void deleteAlbumTest() throws IOException, URISyntaxException {
        photo.deleteAlbum(ACCESS_TOKEN);
        Assert.assertFalse(photo.albumIsPresent(OWNER_ID, ACCESS_TOKEN));
    }
}