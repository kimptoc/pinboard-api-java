package net.kimptoc.pinboard;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class PinboardApiTest {

    private PinboardApi api;
    private String user;
    private String token;

    @Test
    public void queryTagsOk() {
        List<Tag> tags = api.getTags(user, token);
        assertTrue(tags.size() > 10);
        assertEquals("100daystogo", tags.get(0).getTagName());
    }

    @Test
    public void queryPostsOk() {
        List<Post> posts = api.getPosts(user, token, "homesites");
        assertTrue(posts.size() > 10);
//        assertEquals("100daystogo",posts.get(0).getTagName());
    }

    @Before
    public void setUp() {
        api = new PinboardApi();
        Map<String, String> env = System.getenv();
        user = env.get("pinboard.user");
        token = env.get("pinboard.token");
    }

}
