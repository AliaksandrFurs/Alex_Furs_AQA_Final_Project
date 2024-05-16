import business.Post;
import factories.PageFactory;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EditingPostPage;
import pages.LoginPage;
import pages.PostsPage;
import utils.Configuration;
import utils.Logging;

import java.lang.reflect.Method;

public class PostsTest extends BaseTest{

    LoginPage loginPage = PageFactory.getLoginPage(driver);
    EditingPostPage editingPostPage = PageFactory.getEditingPostPage(driver);
    PostsPage postsPage = PageFactory.getPostsPage(driver);

    @BeforeClass
    public void navigateTo(){

        loginPage.openPage();
        loginPage.doRememberMeLogin(Configuration.getLogin(), Configuration.getPassword());
        editingPostPage.openPage();
        Logging.logInfo("Post creating page opened successfully");
    }

    @BeforeMethod
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test (description = "Adding single post test")
    @Severity(SeverityLevel.CRITICAL)
    public void addOnePostTest(){

        Logging.logInfo("Test " + testName + " started");
        editingPostPage.addNewPost(Post.getPostTitle(), Post.getPostBody());
        editingPostPage.dashboardClick();
        postsPage.findPost(Post.getPostTitle());
        Assert.assertTrue(postsPage.isPostAvailable(Post.getPostTitle()), "No such post available");
        Logging.logInfo("Test " + testName + " finished");

    }

    @Test (description = "Save post as draft")
    @Severity(SeverityLevel.NORMAL)
    public void addDraftTest(){

        Logging.logInfo("Test " + testName + " started");
        editingPostPage.savePostAsDraft("Test as draft", "Test as draft");
        editingPostPage.dashboardClick();
        postsPage.findPost("Test as draft");
        Assert.assertTrue(postsPage.isPostDraft("Test as draft"), "Post is not draft");
    }

}
