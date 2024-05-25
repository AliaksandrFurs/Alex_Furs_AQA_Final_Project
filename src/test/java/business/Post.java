package business;

public class Post {

    private final static String postTitle = "Simple post test title";
    private final static String postBody = "Simple post test body to be added during test";

    public static String getPostTitle() {
        return postTitle;
    }

    public static String getPostBody() {
        return postBody;
    }
}
