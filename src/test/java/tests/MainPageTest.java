package tests;

import dataproviders.MainPageTestDataProvider;
import enums.MainMenuBarSectionEnum;
import enums.TopPageBarEnum;
import factories.PageFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.LoginPage;
import pages.MainPage;
import utils.Configuration;
import utils.Logging;

public class MainPageTest extends BaseTest {

    MainPage mainPage = PageFactory.getMainPage(driver);
    LoginPage loginPage = PageFactory.getLoginPage(driver);
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass(alwaysRun = true)
    public void navigateTo(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        mainPage.openPage();
        Logging.logInfo("Main page opens");
    }

    @Test(dataProvider = "top bar elements", dataProviderClass = MainPageTestDataProvider.class)
    public void openingPageFromTopBarTest(TopPageBarEnum sectionTyoe){

        //TBD

    }

    @Test (dataProvider = "main menu bar elements", dataProviderClass = MainPageTestDataProvider.class, groups = "smoke")
    @Severity(SeverityLevel.CRITICAL) @Description("Opening pages from main menu bar")
    public void openingPageFromMainMenuTest(MainMenuBarSectionEnum sectionType, String result){
        Logging.logInfo("Test " + testName + " started");
        BasePage page = mainPage.ClickOnBarSection(sectionType);
        softAssert.assertEquals(page.getPageName(), result, "Page does not opened");
        softAssert.assertAll();
        Logging.logInfo("Test " + testName + " finished");

    }
}
