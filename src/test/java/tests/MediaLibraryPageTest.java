package tests;

import business.Media;
import factories.PageFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MediaLibraryOrdinaryPage;
import pages.UploadNewMediaPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

public class MediaLibraryPageTest extends BaseTest{

    LoginPage loginPage = PageFactory.getLoginPage(driver);
    MediaLibraryOrdinaryPage mediaLibraryPage = PageFactory.getMediaPage(driver);
    UploadNewMediaPage uploadMediaPage = PageFactory.getUploadNewMediaPage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateToAndPrepareData(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        uploadMediaPage.openPage();
        uploadMediaPage.uploadNewImage();
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test(priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Delete media test")
    public void deleteMediaTest(){
        Logging.logInfo("Test " + testName + " started");
        mediaLibraryPage.deleteEntity(Media.getMediaNameTitle());
        Assert.assertFalse(mediaLibraryPage.isEntityAvailable(), "Media still available and was not delete");
    }

    @Test(priority = 1, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Opening media adding form")
    public void openUploadMediaPageTest(){
        mediaLibraryPage.openAddingEntityPage();
        Assert.assertTrue(uploadMediaPage.isOpened(), "Upload media page does not opened");
    }
}
