package tests;

import business.Media;
import factories.PageFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MediaLibraryOrdinaryPage;
import pages.UploadNewMediaPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

public class UploadingMediaTest extends BaseTest {

    LoginPage loginPage = PageFactory.getLoginPage(driver);
    MediaLibraryOrdinaryPage mediaLibraryPage = PageFactory.getMediaPage(driver);
    UploadNewMediaPage uploadMediaPage = PageFactory.getUploadNewMediaPage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateTo(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        uploadMediaPage.openPage();
        Logging.logInfo("Uploading new media page opened successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @AfterMethod
    public void returnToUploadMediaPage(){
        uploadMediaPage.openPage();
    }


    @Test(groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Adding non existing media")
    public void addEmptyMediaTest(){
        Logging.logInfo("Test " + testName + " started");
        uploadMediaPage.uploadEmptyImage();
        Assert.assertTrue(uploadMediaPage.isErrorPageOpens(), "No error occured during adding empty media");
    }
}
