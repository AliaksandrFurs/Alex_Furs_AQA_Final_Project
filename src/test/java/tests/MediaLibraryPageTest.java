package tests;

import business.Media;
import enums.MainMenuBarSectionEnum;
import factories.PageFactory;
import interfaces.pages.ILoginPageInterface;
import interfaces.pages.IPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.AllureReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.UploadNewMediaPage;
import utils.Configuration;
import utils.Logging;
import utils.TestUtils;

@Listeners({AllureReportListener.class})
public class MediaLibraryPageTest extends BaseTest{

    ILoginPageInterface loginPage = PageFactory.getLoginPage(driver);
    IPage mediaLibraryPage = PageFactory.getMediaPage(driver);
    UploadNewMediaPage uploadMediaPage = PageFactory.getUploadNewMediaPage(driver);
    SoftAssert softAssert = new SoftAssert();

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

    @AfterMethod(alwaysRun = true)
    public void returnToMainPage(){
        mediaLibraryPage.openPage();
    }

    @Test(priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Delete media test")
    public void deleteMediaTest(){
        mediaLibraryPage.deleteEntity(Media.getMediaNameTitle());
        Assert.assertFalse(TestUtils.isEntityAvailable(mediaLibraryPage, Media.getMediaNameTitle()), "Media was not delete or not presented");
    }

    @Test(priority = 1, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Add one single valid media")
    public void addOneMediaTest(){
        mediaLibraryPage.openAddingEntityPage();
        uploadMediaPage.uploadNewImage(Media.getMediaNameTitle());
        mediaLibraryPage.searchEntity(Media.getMediaNameTitle());
        softAssert.assertTrue(TestUtils.isEntityAvailable(mediaLibraryPage, Media.getMediaNameTitle()));
        softAssert.assertTrue(TestUtils.verifyIsAuthorCorrect(mediaLibraryPage, "admin admin"));
        softAssert.assertTrue(TestUtils.verifyIsDateCorrect(mediaLibraryPage,"2024/06/02"));
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Adding non existing media")
    public void addEmptyMediaTest(){
        mediaLibraryPage.openAddingEntityPage();
        uploadMediaPage.uploadNewImage("");
        Assert.assertTrue(uploadMediaPage.isOpened(), "No error occured during adding empty media");
    }
}
