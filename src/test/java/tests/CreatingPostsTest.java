package tests;

import business.Post;
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
import pages.PostsOrdinaryPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

public class CreatingPostsTest extends BaseTest {

    LoginPage loginPage = PageFactory.getLoginPage(driver);
    CreatePage createPage = PageFactory.getCreatePage(driver);
    PostsOrdinaryPage postsPage = PageFactory.getPostsPage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateTo(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        createPage.openPage(AddingEntityTypeEnum.POST);
        Logging.logInfo("Post creating page opened successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test (priority = 1, groups = "smoke")
    @Severity(SeverityLevel.CRITICAL) @Description("Adding single post test")
    public void addOnePostTest(){
        Logging.logInfo("Test " + testName + " started");
        createPage.addNewEntity(Post.getPostTitle(), Post.getPostBody());
        createPage.dashboardClick();
        postsPage.searchEntity(Post.getPostTitle());
        Assert.assertTrue(postsPage.isEntityAvailable(), "No such post available");
        Logging.logInfo("Test " + testName + " finished");

    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Save post as draft")
    public void addPostDraftTest(){
        Logging.logInfo("Test " + testName + " started");
        createPage.saveEntityAsDraft("Test as draft", "Test as draft");
        createPage.dashboardClick();
        postsPage.searchEntity("Test as draft");
        Assert.assertTrue(postsPage.isPostDraft("Test as draft"), "Post is not draft");
    }

}
