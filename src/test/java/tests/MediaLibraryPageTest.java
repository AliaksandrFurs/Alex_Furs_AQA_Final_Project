package tests;

import business.Media;
import enums.MainMenuBarSectionEnum;
import factories.PageFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.AllureReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MediaLibraryOrdinaryPage;
import pages.UploadNewMediaPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

@Listeners({AllureReportListener.class})
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
        mediaLibraryPage.ClickOnBarSection(MainMenuBarSectionEnum.MEDIA);
        Logging.logInfo("Media page opened successfully");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        mediaLibraryPage.ClickOnBarSection(MainMenuBarSectionEnum.MEDIA);
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    public void returnToMainPage(){
        mediaLibraryPage.openPage();
    }

    @Test(priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Delete media test")
    public void deleteMediaTest(){
        Logging.logInfo("Test " + testName + " started");
        mediaLibraryPage.deleteEntity(Media.getMediaNameTitle());
        Assert.assertFalse(mediaLibraryPage.isEntityAvailable(), "Media still available and was not delete");
    }

    @Test(priority = 1, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Add one single valid media")
    public void addOneMediaTest(){
        mediaLibraryPage.openAddingEntityPage();
        uploadMediaPage.uploadNewImage(Media.getMediaNameTitle());
        mediaLibraryPage.searchEntity(Media.getMediaNameTitle());
        Assert.assertTrue(mediaLibraryPage.isEntityAvailable());
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test(priority = 3, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Adding non existing media")
    public void addEmptyMediaTest(){
        Logging.logInfo("Test " + testName + " started");
        mediaLibraryPage.openAddingEntityPage();
        uploadMediaPage.uploadNewImage("");
        Assert.assertTrue(uploadMediaPage.isOpened(), "No error occured during adding empty media");
    }
}
