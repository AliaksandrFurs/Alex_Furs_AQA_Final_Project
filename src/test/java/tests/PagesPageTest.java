package tests;

import business.Page;
import enums.AddingEntityTypeEnum;
import enums.MainMenuBarSectionEnum;
import factories.PageFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.AllureReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CreatePage;
import pages.LoginPage;
import pages.PagesOrdinaryPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

@Listeners({AllureReportListener.class})
public class PagesPageTest extends BaseTest{

    PagesOrdinaryPage pagesPage = PageFactory.getPagesPage(driver);
    LoginPage loginPage = PageFactory.getLoginPage(driver);
    CreatePage createPage = PageFactory.getCreatePage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateToAndPrepareData(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        pagesPage.ClickOnBarSection(MainMenuBarSectionEnum.PAGES);
        //createPage.addNewEntity(Page.getPostTitle(), Page.getPostBody());
        //pagesPage.openPage();
        Logging.logInfo("Pages page opened successfully");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        pagesPage.ClickOnBarSection(MainMenuBarSectionEnum.PAGES);
        //pagesPage.deleteEntity("Test as draft");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    public void returnToMainPage(){
        pagesPage.ClickOnBarSection(MainMenuBarSectionEnum.PAGES);
    }

    @Test(priority = 1, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Adding one single page test")
    public void addOnePageTest(){
        Logging.logInfo("Test " + testName + " starts");
        pagesPage.openAddingEntityPage();
        createPage.addNewEntity(Page.getPostTitle(), Page.getPostBody());
        pagesPage.searchEntity(Page.getPostTitle());
        Assert.assertTrue(pagesPage.isEntityAvailable(), "Page was not added");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Find specific page")
    public void findPageTest(){
        Logging.logInfo("Test " + testName + " started");
        pagesPage.searchEntity(Page.getPostTitle());
        Assert.assertTrue(pagesPage.isEntityAvailable(), "Page does not found");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 3, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Delete page test")
    public void deletePageTest(){
        Logging.logInfo("Test " + testName + " started");
        pagesPage.deleteEntity(Page.getPostTitle());
        Assert.assertFalse(pagesPage.isEntityAvailable(), "Page still available and was not delete");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 4, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Save page as draft")
    public void addPageDraftTest(){
        Logging.logInfo("Test " + testName + " started");
        pagesPage.openAddingEntityPage();
        createPage.saveEntityAsDraft("Test as draft", "Test as draft");
        pagesPage.searchEntity("Test as draft");
        Assert.assertTrue(pagesPage.isPageDraft("Test as draft"), "Post is not draft");
    }
}
