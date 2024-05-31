package listeners;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Browser;
import utils.Logging;

import java.io.ByteArrayInputStream;


public class AllureReportListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Logging.logInfo("Test " + getTestName(result) + " started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot)Browser.getDriver()).getScreenshotAs(OutputType.BYTES)));
        Logging.logInfo("Test " + getTestName(result) + " finished successfully");
    }

    @Override
    public void onTestFailure(ITestResult result){
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot)Browser.getDriver()).getScreenshotAs(OutputType.BYTES)));
        Logging.logInfo("Test " + getTestName(result) + " finished unsuccessfully");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logging.logInfo("Test " + getTestName(result) + " skipped");
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
        Logging.logInfo("Tests started");
    }

    @Override
    public void onFinish(ITestContext context) {
        Logging.logInfo("Tests finished");
    }

    private static String getTestName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

}
