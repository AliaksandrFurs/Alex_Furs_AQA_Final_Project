package listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Browser;

public class AllureReportListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){

        WebDriver driver = Browser.getDriver();
        if(driver instanceof WebDriver && driver != null){
            screenshot(driver);
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
