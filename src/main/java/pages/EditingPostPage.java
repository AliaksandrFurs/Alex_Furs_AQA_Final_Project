package pages;

import elements.interfaces.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditingPostPage extends BasePage implements Page {

    private final static String EDITINGPOSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/post-new.php";

    private By addTitleField = By.xpath("//h1[contains(@class, 'wp-block wp-block-post-title " +
            "block-editor-block-list__block editor-post-title editor-post-title__input')]");

    public EditingPostPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public void openPage() {

    }

    public void addNewPost(String postTitle, String postBody){


    }
}
