package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.Browser;
import utils.Configuration;
import utils.Logging;

public class BaseTest {

    public String testName;

    static Browser browser = new Browser();
    static WebDriver driver = Browser.getDriver();
    static boolean isUserLogin = false;

    @BeforeSuite(alwaysRun = true)
    public void basicSetup(){

        browser.browserSetup();
        Logging.logInfo("Browser setup successfull");
    }

    @AfterSuite(description = "Close browser", alwaysRun = true)
    static void close (){

        browser.browserClose();
        Logging.logInfo("Browser close");

    }
}
