package pages;

import org.openqa.selenium.WebDriver;

public class CommentsPage extends BasePage{

    private final static String COMMENTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit-comments.php";

    private CommentsPage(WebDriver driver) {
        super(driver);
    }
}
