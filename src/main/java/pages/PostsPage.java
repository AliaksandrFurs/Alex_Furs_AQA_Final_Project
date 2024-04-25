package pages;

import elements.tables.PostsPageTable;
import org.openqa.selenium.WebDriver;

public class PostsPage extends BasePage{


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";

    private PostsPageTable postsPageTable = new PostsPageTable(driver);

    public PostsPage(WebDriver driver) {
        super(driver);
    }
}
