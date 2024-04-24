package pages;

import elements.tables.MediaPageTable;
import org.openqa.selenium.WebDriver;

public class MediaPage extends BasePage {

    private final static String MEDIA_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/upload.php";
    private MediaPageTable mediaPageTable= new MediaPageTable(driver);


    private MediaPage(WebDriver driver){
        super(driver);
    }
}
