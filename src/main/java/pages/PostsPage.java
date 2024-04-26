package pages;

import elements.interfaces.Page;
import elements.tables.PostsPageTable;
import org.openqa.selenium.WebDriver;
import utils.Wait;

public class PostsPage extends BasePage implements Page {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";

    private PostsPageTable postsPageTable = new PostsPageTable(driver);

    public PostsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {

        driver.get(POSTS_URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
        //postsPageTable.createTableRows();
    }
}
