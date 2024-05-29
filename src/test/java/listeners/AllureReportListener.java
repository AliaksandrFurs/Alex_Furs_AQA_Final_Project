package listeners;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Browser;

import java.io.ByteArrayInputStream;


public class AllureReportListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        WebDriver driver = Browser.getDriver();
        if(driver instanceof WebDriver && driver != null){
            screenshot(driver);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot)Browser.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    public void onTestFailure(ITestResult result){
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot)Browser.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

    @Attachment()
    public static byte[] screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
