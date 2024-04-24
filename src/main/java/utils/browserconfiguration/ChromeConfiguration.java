package utils.browserconfiguration;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfiguration {

    private ChromeOptions chromeConfiguration = new ChromeOptions();

    public ChromeConfiguration(){

        chromeConfiguration.setHeadless(false);
        chromeConfiguration.setExperimentalOption("excludeSwitches", "disable-popup-blocking");
    }
}