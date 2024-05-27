package business;

public class Page {

    private static String pageTitle = "Simple page test title";
    private static String pageBody = "Simple page test body to be added during test";
    private static String newPageTitle = "New page test title";
    private static String newPageBody = "New page test body";

    public static String getPostTitle() {
        return pageTitle;
    }
    public static String getPostBody() {
        return pageBody;
    }
    public static String getNewPageTitle(){return newPageTitle;}
    public static String getNewPageBody(){return newPageBody;}

}
