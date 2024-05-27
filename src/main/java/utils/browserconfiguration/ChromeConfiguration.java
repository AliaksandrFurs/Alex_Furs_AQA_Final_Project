package utils.browserconfiguration;

import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeConfiguration {

    private ChromeOptions chromeConfiguration = new ChromeOptions();

    public ChromeConfiguration(){

        //chromeConfiguration.setExperimentalOption("excludeSwitches", "disable-popup-blocking");
        chromeConfiguration.setImplicitWaitTimeout(Duration.ofSeconds(30));
    }
}
