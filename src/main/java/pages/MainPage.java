package pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    private final static String DASHBOARD_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/index.php";

    private MainPage(WebDriver driver){
        super(driver);
        pageName = "Dashboard";
    }


}
