import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wall {
    private String postID;
    private Logger logger = Logger.getLogger(Wall.class);

    public void postMessage(String message, String ownerID, String accessToken) {
        HttpClient client = HttpClientBuilder.create().build();
        try {
            URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.post?");
            builder.setParameter("access_token", accessToken)
                    .setParameter("owner_id", ownerID)
                    .setParameter("message", message)
                    .setParameter("v", "5.103");
            HttpGet request = new HttpGet(builder.build());
            HttpResponse response = client.execute(request);
            Pattern pattern = Pattern.compile("\\d+");
            String postIdResponse = EntityUtils.toString(response.getEntity());
            Matcher matcher = pattern.matcher(postIdResponse);
            while (matcher.find()) {
                postID = matcher.group();
            }
            logger.info("Message is posted");
        } catch (IOException | URISyntaxException exception) {
            logger.fatal(exception);
        }
    }

    public void editMessage(String newText, String ownerID, String accessToken) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.edit?");
            builder.setParameter("access_token", accessToken)
                    .setParameter("owner_id", ownerID)
                    .setParameter("message", newText)
                    .setParameter("post_id", postID)
                    .setParameter("v", "5.103");
            HttpGet request = new HttpGet(builder.build());
            client.execute(request);
            logger.info("Message is edited");
        } catch (IOException | URISyntaxException exception) {
            logger.fatal(exception);
        }
    }

    public void deleteMessage(String ownerID, String accessToken) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.delete?");
            builder.setParameter("access_token", accessToken)
                    .setParameter("owner_id", ownerID)
                    .setParameter("post_id", postID)
                    .setParameter("v", "5.103");
            HttpGet request = new HttpGet(builder.build());
            client.execute(request);
            logger.info("Message is deleted");
        } catch (IOException | URISyntaxException exception) {
            logger.fatal(exception);
        }
    }

    public boolean postIsPresent(String ownerID, String accessToken) {
        String regex = "\"id\":" + postID;
        return findMatchesOnWallByRegEx(regex, ownerID, accessToken);
    }

    public boolean postIsEdited(String editedMessage, String ownerID, String accessToken) {
        String regex = "\"id\":" + postID + ".+?text\":\"" + editedMessage;
        return findMatchesOnWallByRegEx(regex, ownerID, accessToken);
    }

    public boolean findMatchesOnWallByRegEx(String regex, String ownerID, String accessToken) {
        HttpClient client = HttpClientBuilder.create().build();
        String postIdsResponse;
        boolean matches = false;
        try {
            URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.get?");
            builder.setParameter("access_token", accessToken)
                    .setParameter("owner_id", ownerID)
                    .setParameter("v", "5.103");
            HttpGet request = new HttpGet(builder.build());
            HttpResponse response = client.execute(request);
            postIdsResponse = EntityUtils.toString(response.getEntity());
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(postIdsResponse);
            matches = matcher.find();
        } catch (IOException | URISyntaxException exception) {
            logger.fatal(exception);
        }
        return matches;
    }
}