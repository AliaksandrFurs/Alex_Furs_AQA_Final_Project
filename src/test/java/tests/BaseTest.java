package tests;

import factories.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.Browser;
import utils.Configuration;
import utils.Logging;
import utils.browserconfiguration.ChromeConfiguration;

public class BaseTest {

    public String testName;

    static Browser browser = new Browser();
    static WebDriver driver = Browser.getDriver();
    static boolean isUserLogin = false;

    @BeforeClass(alwaysRun = true)
    public void basicSetup(){
        browser.browserSetup();
        Logging.logInfo("Browser setup successfull");
    }

    @AfterClass(description = "Close browser", alwaysRun = true)
     void close (){
        browser.browserClose();
        Logging.logInfo("Browser close");
    }

}
