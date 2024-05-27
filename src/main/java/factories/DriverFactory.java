package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Logging;
import utils.browserconfiguration.ChromeConfiguration;
import utils.browserconfiguration.FirefoxConfiguration;


public class DriverFactory {

    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\37529\\IdeaProjects\\Alex_Furs_AQA_Final_Project\\Drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\37529\\IdeaProjects\\Alex_Furs_AQA_Final_Project\\Drivers\\geckodriver.exe");
    }

    private static ThreadLocal<WebDriver> driver;

    private DriverFactory() {
    }

    public static WebDriver getChromeDriver(ChromeConfiguration chromeConfiguration) {
        if (driver == null) {
            driver = new ThreadLocal<>();
            driver.set(new ChromeDriver());
            Logging.logInfo("New chrome driver created successfully");

        }
        return driver.get();
    }

    public static WebDriver getGeckoDriver(FirefoxConfiguration firefoxConfiguration){

        if(driver == null){
            driver = new ThreadLocal<>();
            driver.set(new FirefoxDriver());
            Logging.logInfo("New firefox driver created successfully");
        }
       return driver.get();
    }
}
