package utils;

import factories.DriverFactory;
import org.openqa.selenium.WebDriver;
import utils.browserconfiguration.ChromeConfiguration;

public class Browser {

    private static WebDriver driver;

    public Browser(){

        String browserName = Configuration.getBrowserName();

        switch(browserName){

            case "Chrome":

                driver = DriverFactory.getChromeDriver(new ChromeConfiguration());
                break;

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
