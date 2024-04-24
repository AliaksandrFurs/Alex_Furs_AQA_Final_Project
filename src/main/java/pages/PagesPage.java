package pages;

import org.openqa.selenium.WebDriver;

public class PagesPage extends  BasePage{

    private final static String PAGE_UTL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php?post_type=page";

    private PagesPage(WebDriver driver){

        super(driver);
    }
}
