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
        createPage.openPage(AddingEntityTypeEnum.POST);
        createPage.addNewEntity(Post.getPostTitle(), Post.getPostBody());
        postsPage.openPage();
        Logging.logInfo("Post created successfully");
        Logging.logInfo("Posts page opened successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test (priority = 1, groups = "regression")
    @Severity(SeverityLevel.CRITICAL) @Description("Opening post adding form")
    public void openPostAddingPageTest(){
        Logging.logInfo("Test " + testName + " starts");
        postsPage.openAddingEntityPage();
        Assert.assertTrue(createPage.isOpened(), "Post adding page does not opened");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Find specific post")
    public void findPostTest(){
        Logging.logInfo("Test " + testName + " started");
        postsPage.searchEntity(Post.getPostTitle());
        Assert.assertTrue(postsPage.isEntityAvailable(), "Post does not found");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (priority = 3, groups = "smoke")
    @Severity(SeverityLevel.CRITICAL) @Description("Delete post test")
    public void deletePostTest(){
        Logging.logInfo("Test " + testName + " started");
        postsPage.deleteEntity(Post.getPostTitle());
        Assert.assertFalse(postsPage.isEntityAvailable(), "Post still available and was not delete");
        Logging.logInfo("Test " + testName + " finished");
    }

}
