package tests;

import business.Page;
import enums.AddingEntityTypeEnum;
import factories.PageFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreatePage;
import pages.LoginPage;
import pages.PagesOrdinaryPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

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
        createPage.openPage(AddingEntityTypeEnum.PAGE);
        createPage.addNewEntity(Page.getPostTitle(), Page.getPostBody());
        pagesPage.openPage();
        Logging.logInfo("Page created successfully");
        Logging.logInfo("Pages page opened successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test(priority = 1, groups = "regression")
    @Severity(SeverityLevel.CRITICAL) @Description("Opening page adding form")
    public void openPageAddingPageTest(){
        Logging.logInfo("Test " + testName + " starts");
        pagesPage.openAddingEntityPage();
        Assert.assertTrue(createPage.isOpened(), "Adding page does not opened");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Find specific page")
    public void findPageTest(){
        Logging.logInfo("Test " + testName + " started");
        pagesPage.searchEntity(Page.getPostTitle());
        Assert.assertEquals(pagesPage.isEntityAvailable(), "Page does not found");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 3, groups = "smoke")
    @Severity(SeverityLevel.CRITICAL) @Description("Delete page test")
    public void deletePageTest(){
        Logging.logInfo("Test " + testName + " started");
        pagesPage.deleteEntity(Page.getPostTitle());
        Assert.assertFalse(pagesPage.isEntityAvailable(), "Page still available and was not delete");
        Logging.logInfo("Test " + testName + " finished");
    }
}
