package tests;

import factories.PageFactory;
import interfaces.pages.ILoginPageInterface;
import interfaces.pages.IMainPageInterface;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.AllureReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Configuration;
import utils.Logging;


@Listeners({AllureReportListener.class})
public class LoginTest extends BaseTest {

    ILoginPageInterface loginPage = PageFactory.getLoginPage(driver);
    IMainPageInterface mainPage = PageFactory.getMainPage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateTo(){
        loginPage.openPage();
        Logging.logInfo("Login page opened successfully");
    }


    @Test(groups = "smoke", enabled = false)
    @Severity(SeverityLevel.CRITICAL) @Description("Successfull login test")
    public void successfullLoginTest(){
        loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
        Assert.assertTrue(mainPage.isOpened());
    }

    @Test(groups = {"smoke", "regression"}, priority = 1)
    @Severity(SeverityLevel.CRITICAL) @Description("Unsuccessfull login test")
    public void unsuccessfullLoginTest(){
        loginPage.doLogin(Configuration.getInvalidLogin(), Configuration.getInvalidPassword(), false);
        Assert.assertTrue(loginPage.isOpened());
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.MINOR) @Description("Enable password chars")
    public void enablePasswrodCharsTest(){
        Assert.assertTrue(loginPage.isPasswordMasked(Configuration.getInvalidPassword()), "Password is not unmasked");
    }
}
