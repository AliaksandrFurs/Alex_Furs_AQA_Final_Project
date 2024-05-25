package tests;

import factories.PageFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.Configuration;
import utils.Logging;

public class LoginTest extends BaseTest {

    LoginPage loginPage = PageFactory.getLoginPage(driver);
    MainPage mainPage = PageFactory.getMainPage(driver);

    @BeforeClass(groups = {"smore,regression"})
    public void navigateTo(){
        loginPage.openPage();
        Logging.logInfo("Login page opened successfully");
    }

    @Test(groups = "smoke")
    @Severity(SeverityLevel.CRITICAL) @Description("Successfull login test")
    public void successfullLoginTest(){
        loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
        Assert.assertTrue(mainPage.isOpened());
        Logging.logInfo("Login successfull");
    }

    @Test(groups = "smoke")
    @Severity(SeverityLevel.CRITICAL) @Description("Unsuccessfull login test")
    public void unsuccessfullLoginTest(){
        loginPage.doLogin(Configuration.getInvalidLogin(), Configuration.getInvalidPassword(), false);
        Assert.assertTrue(loginPage.isOpened(), "Login successfully done but shouldn't!");
        Logging.logInfo("Login unsuccessfull");
    }
}
