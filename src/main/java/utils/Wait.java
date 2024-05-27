package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {

    //private static final int DEFAULT_WAIT_IN_SEC = 20;
    private static WebDriver driver = Browser.getDriver();
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public static void isElementPresented(WebElement element){

        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void isElementDisplayed(WebElement element){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }
}
