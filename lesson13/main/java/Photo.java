import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Photo {
    private String albumID;
    private String uploadServer;

    public void createAlbum(String accessToken, String title)
            throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://api.vk.com/method/photos.createAlbum?");
        builder.setParameter("access_token", accessToken)
                .setParameter("title", title)
                .setParameter("v", "5.103");
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        String albumIdResponse = EntityUtils.toString(response.getEntity());
        Pattern pattern = Pattern.compile("\"id\":\\d+");
        Matcher matcher = pattern.matcher(albumIdResponse);
        while (matcher.find()) {
            albumID = matcher.group().substring(5);
        }
        getUploadURL(accessToken);
    }

    public void getUploadURL(String accessToken) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://api.vk.com/method/photos.getUploadServer?");
        builder.setParameter("access_token", accessToken)
                .setParameter("album_id", albumID)
                .setParameter("v", "5.103");
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        String albumIdResponse = EntityUtils.toString(response.getEntity());
        Pattern pattern = Pattern.compile("https.+?api=1");
        Matcher matcher = pattern.matcher(albumIdResponse);
        while (matcher.find()) {
            uploadServer = matcher.group().replace("\\", "");
        }
    }

    public void deleteAlbum(String accessToken)
            throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://api.vk.com/method/photos.deleteAlbum?");
        builder.setParameter("access_token", accessToken)
                .setParameter("album_id", albumID)
                .setParameter("v", "5.103");
        HttpGet request = new HttpGet(builder.build());
        client.execute(request);
    }

    public boolean albumIsPresent(String ownerID, String accessToken)
            throws URISyntaxException, IOException {
        String regex = "\"id\":" + albumID;
        return findMatchesInAlbumsByRegEx(regex, ownerID, accessToken);
    }

    public boolean findMatchesInAlbumsByRegEx(String regex, String ownerID, String accessToken)
            throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://api.vk.com/method/photos.getAlbums?");
        builder.setParameter("access_token", accessToken)
                .setParameter("owner_id", ownerID)
                .setParameter("v", "5.103");
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        Pattern pattern = Pattern.compile(regex);
        String albumsIdsResponse = EntityUtils.toString(response.getEntity());
        Matcher matcher = pattern.matcher(albumsIdsResponse);
        return matcher.find();
    }
}