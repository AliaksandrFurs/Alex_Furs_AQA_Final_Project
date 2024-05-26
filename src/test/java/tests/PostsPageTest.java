package tests;

import business.Post;
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
import pages.PostsOrdinaryPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

@Listeners({AllureReportListener.class})
public class PostsPageTest extends BaseTest {

    PostsOrdinaryPage postsPage = PageFactory.getPostsPage(driver);
    LoginPage loginPage = PageFactory.getLoginPage(driver);
    CreatePage createPage = PageFactory.getCreatePage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateToAndPrepareData(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        postsPage.ClickOnBarSection(MainMenuBarSectionEnum.POSTS);
        //createPage.addNewEntity(Post.getPostTitle(), Post.getPostBody());
        //postsPage.openPage();
        Logging.logInfo("Posts page opened successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    public void returnToMainPage(){
        postsPage.ClickOnBarSection(MainMenuBarSectionEnum.POSTS);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        postsPage.ClickOnBarSection(MainMenuBarSectionEnum.POSTS);
        //postsPage.deleteEntity("Test as draft");
    }

    @Test (priority = 1, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Adding one single post test") @Parameters("browser")
    public void addOnePostTest(){
        Logging.logInfo("Test " + testName + " starts");
        postsPage.openAddingEntityPage();
        createPage.addNewEntity(Post.getPostTitle(), Post.getPostBody());
        postsPage.searchEntity(Post.getPostTitle());
        Assert.assertTrue(postsPage.isEntityAvailable(), "Post was not added");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Find specific post") @Parameters("browser")
    public void findPostTest(){
        Logging.logInfo("Test " + testName + " started");
        postsPage.searchEntity(Post.getPostTitle());
        Assert.assertTrue(postsPage.isEntityAvailable(), "Post does not found");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 3, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Delete post test") @Parameters("browser")
    public void deletePostTest(){
        Logging.logInfo("Test " + testName + " started");
        postsPage.deleteEntity(Post.getPostTitle());
        Assert.assertFalse(postsPage.isEntityAvailable(), "Post still available and was not delete");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 4, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Save post as draft") @Parameters("browser")
    public void addPostDraftTest(){
        Logging.logInfo("Test " + testName + " started");
        postsPage.openAddingEntityPage();
        createPage.saveEntityAsDraft("Test as draft", "Test as draft");
        postsPage.searchEntity("Test as draft");
        Assert.assertTrue(postsPage.isPostDraft("Test as draft"), "Post is not draft");
    }
}
