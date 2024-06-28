package tests;

import business.Page;
import enums.MainMenuBarSectionEnum;
import factories.PageFactory;
import interfaces.pages.ICreatePageInterface;
import interfaces.pages.ILoginPageInterface;
import interfaces.pages.IPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.AllureReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Configuration;
import utils.Logging;
import utils.TestUtils;

@Listeners({AllureReportListener.class})
public class PagesPageTest extends BaseTest{

    IPage pagesPage = PageFactory.getPagesPage(driver);
    ILoginPageInterface loginPage = PageFactory.getLoginPage(driver);
    ICreatePageInterface createPage = PageFactory.getCreatePage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateToAndPrepareData(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        pagesPage.ClickOnBarSection(MainMenuBarSectionEnum.PAGES);
        Logging.logInfo("Pages page opened successfully");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        pagesPage.deleteEntity("Test as draft");
        pagesPage.ClickOnBarSection(MainMenuBarSectionEnum.PAGES);
    }

    @AfterMethod(alwaysRun = true)
    public void returnToMainPage(){
        pagesPage.ClickOnBarSection(MainMenuBarSectionEnum.PAGES);
    }

    @Test(priority = 1, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Adding one single page test")
    public void addOnePageTest(){
        pagesPage.openAddingEntityPage();
        createPage.addNewEntity(Page.getPostTitle(), Page.getPostBody());
        pagesPage.searchEntity(Page.getPostTitle());
        Assert.assertTrue(TestUtils.isEntityAvailable(pagesPage, Page.getPostTitle()), "Page was not added");
    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Find specific page")
    public void findPageTest(){
        pagesPage.searchEntity("Page.getPostTitle()");
        Assert.assertTrue(TestUtils.isEntityAvailable(pagesPage, Page.getPostTitle()), "Page does not found");
    }

    @Test (priority = 5, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Delete page test")
    public void deletePageTest(){
        pagesPage.deleteEntity(Page.getNewPageTitle());
        Assert.assertFalse(TestUtils.isEntityAvailable(pagesPage, Page.getNewPageTitle()), "Page still available");
    }

    @Test (priority = 3, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Save page as draft")
    public void addPageDraftTest(){
        pagesPage.openAddingEntityPage();
        createPage.saveEntityAsDraft("Test as draft", "Test as draft");
        pagesPage.searchEntity("Test as draft");
        Assert.assertTrue(TestUtils.isEntityDraft(pagesPage,"Test as draft"), "Post is not draft");
    }

    @Test(priority = 4, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Edit existing page")
    public void editPageTest(){
        pagesPage.clickOnEntity(Page.getPostTitle());
        createPage.updateEntity(Page.getNewPageTitle(), Page.getNewPageBody());
        pagesPage.searchEntity(Page.getNewPageTitle());
        Assert.assertTrue(TestUtils.isEntityWasUpdate(pagesPage, Page.getNewPageTitle()), "Page was not edited");
    }
}
