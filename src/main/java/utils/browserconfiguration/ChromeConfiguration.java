package utils.browserconfiguration;

import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeConfiguration {

    private final ChromeOptions chromeConfiguration = new ChromeOptions();

    public ChromeConfiguration(){

        chromeConfiguration.setImplicitWaitTimeout(Duration.ofSeconds(30));

    }
}
