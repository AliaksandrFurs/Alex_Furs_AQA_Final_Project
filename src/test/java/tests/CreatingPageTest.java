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

public class CreatingPageTest extends BaseTest {

    LoginPage loginPage = PageFactory.getLoginPage(driver);
    PagesOrdinaryPage pagesPage = PageFactory.getPagesPage(driver);
    CreatePage createPage = PageFactory.getCreatePage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateTo(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        createPage.openPage(AddingEntityTypeEnum.PAGE);
        Logging.logInfo("Creating page opened successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test( priority = 1, groups = "smoke")
    @Severity(SeverityLevel.CRITICAL) @Description("Adding single page test")
    public void addOnePageTest(){
        Logging.logInfo("Test " + testName + " started");
        createPage.addNewEntity(Page.getPostTitle(), Page.getPostBody());
        createPage.dashboardClick();
        pagesPage.searchEntity(Page.getPostTitle());
        Assert.assertTrue(pagesPage.isEntityAvailable(), "No such post available");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Save page as draft")
    public void addPageDraftTest(){
        Logging.logInfo("Test " + testName + " started");
        createPage.saveEntityAsDraft("Test as draft", "Test as draft");
        createPage.dashboardClick();
        pagesPage.searchEntity("Test as draft");
        Assert.assertTrue(pagesPage.isPageDraft("Test as draft"), "Post is not draft");
    }
}
