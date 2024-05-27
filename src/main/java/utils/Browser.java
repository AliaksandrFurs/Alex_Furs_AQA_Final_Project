package utils;

import factories.DriverFactory;
import org.openqa.selenium.WebDriver;
import utils.browserconfiguration.ChromeConfiguration;
import utils.browserconfiguration.FirefoxConfiguration;

public class Browser {

    private static WebDriver driver;


    public Browser(){

        String browserName = System.getProperty("browser");

        switch(browserName){
            case "CHROME":
                driver = DriverFactory.getChromeDriver(new ChromeConfiguration());
                //break;
            case "FIREFOX":
                driver = DriverFactory.getGeckoDriver(new FirefoxConfiguration());
                //break;
        }
    }

    public void browserClose(){
        driver.close();
    }

    public  void browserSetup() {
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
