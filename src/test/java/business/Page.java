package business;

public class Page {

    private final static String pageTitle = "Simple page test title";
    private final static String pageBody = "Simple page test body to be added during test";

    public static String getPostTitle() {
        return pageTitle;
    }

    public static String getPostBody() {
        return pageBody;
    }
}
