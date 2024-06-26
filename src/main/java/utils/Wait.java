package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {

    private static WebDriver driver = Browser.getDriver();
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public static void isElementPresented(WebElement element){

        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void isFrameDisplayed(WebElement element){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

}
