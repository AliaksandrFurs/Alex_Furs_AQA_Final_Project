package business;

public class Post {

    private final static String postTitle = "Simple post test title";
    private final static String postBody = "Simple post test body to be added during test";
    private static String newPostTitle = "New post test title";
    private static String newPostBody = "New post test body";

    public static String getPostTitle() {
        return postTitle;
    }
    public static String getPostBody() {
        return postBody;
    }
    public static String getNewPostTitle(){return newPostTitle;}
    public static String getNewPostBody(){return newPostBody;}
}
