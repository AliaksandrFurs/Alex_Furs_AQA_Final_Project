
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Browser;
import utils.Logging;

public class BaseTest {

    public String testName;

    static Browser browser = new Browser();
    static WebDriver driver = Browser.getDriver();

    @BeforeClass
    public void basicSetup(){

        browser.browserSetup();
        Logging.logInfo("Browser setup successfull");
    }

    @AfterClass(description = "Close browser", alwaysRun = true)
    static void close (){

        browser.browserClose();
        Logging.logInfo("Browser close");

    }
}
