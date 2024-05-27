package utils.browserconfiguration;

import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxConfiguration {

    private FirefoxOptions firefoxConfiguration = new FirefoxOptions();

    public FirefoxConfiguration() {
        firefoxConfiguration.setImplicitWaitTimeout(Duration.ofSeconds(30));
    }
}
