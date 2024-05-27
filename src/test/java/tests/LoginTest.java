package tests;

import factories.PageFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.AllureReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.Configuration;
import utils.Logging;
import utils.ScreenshotMaker;

import java.lang.reflect.Method;

@Listeners({AllureReportListener.class})
public class LoginTest extends BaseTest {

    LoginPage loginPage = PageFactory.getLoginPage(driver);
    MainPage mainPage = PageFactory.getMainPage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateTo(){
        loginPage.openPage();
        Logging.logInfo("Login page opened successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test(groups = "smoke", enabled = false)
    @Severity(SeverityLevel.CRITICAL) @Description("Successfull login test")
    public void successfullLoginTest(){
        Logging.logInfo("Test " + testName + " started");
        loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
        Assert.assertTrue(mainPage.isOpened());
        Logging.logInfo("Login successfull");
    }

    @Test(groups = {"smoke", "regression"}, priority = 1)
    @Severity(SeverityLevel.CRITICAL) @Description("Unsuccessfull login test")
    public void unsuccessfullLoginTest(){
        Logging.logInfo("Test " + testName + " started");
        loginPage.doLogin(Configuration.getInvalidLogin(), Configuration.getInvalidPassword(), false);
        Assert.assertFalse(loginPage.isOpened(), "Login successfully done but shouldn't!"); //test should be failed for demonstration!
        Logging.logInfo("Login unsuccessfull");
    }
}
