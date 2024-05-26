package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Browser;
import utils.ScreenshotMaker;

public class AllureReportListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){

        Object testClass = result.getInstance();
        WebDriver driver = Browser.getDriver();
        if(driver instanceof WebDriver && driver != null){
            ScreenshotMaker.screenshot(driver);
        }
    }
}
