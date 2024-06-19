package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.Browser;
import utils.Logging;

public class BaseTest {

    static Browser browser = new Browser();
    static WebDriver driver = Browser.getDriver();
    static boolean isUserLogin = false;

    @BeforeSuite(alwaysRun = true)
    public void basicSetup(){
        browser.browserSetup();
        Logging.logInfo("Browser setup successfull");
    }

    @AfterSuite(description = "Close browser", alwaysRun = true)
     void close (){
        browser.browserClose();
        Logging.logInfo("Browser close");
    }

}
