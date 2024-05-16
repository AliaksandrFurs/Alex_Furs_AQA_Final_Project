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

public class PostsPageTest extends BaseTest {

    //class to be determinated

    PostsPage postsPage = PageFactory.getPostsPage(driver);
    LoginPage loginPage = PageFactory.getLoginPage(driver);
    EditingPostPage editingPostPage = PageFactory.getEditingPostPage(driver);

    @BeforeClass
    public void navigateTo(){

        loginPage.openPage();
        loginPage.doRememberMeLogin(Configuration.getLogin(), Configuration.getPassword());
        postsPage.openPage();
        Logging.logInfo("Posts page opened successfully");
    }

    @BeforeMethod
    public void handleTestMethodeName(Method method){
        testName = method.getName();
    }

    @Test (description = "Opening post adding form")
    @Severity(SeverityLevel.CRITICAL)
    public void openPostAddingPageTest(){

        Logging.logInfo("Test " + testName + " starts");
        postsPage.clickOnAddNewPostButton();
        Assert.assertTrue(editingPostPage.isOpened(), "Post adding page does not opened");
        Logging.logInfo("Test " + testName + " finished");
    }

    @Test (description = "Find specific post")
    @Severity(SeverityLevel.NORMAL)
    public void findPostTest(){

        //TBD
        Logging.logInfo("Test " + testName + " started");
        postsPage.findPost("555");
        Assert.assertEquals(postsPage.isPostAvailable("555"), "555");
        Logging.logInfo("Test " + testName + " finished");

    }

    @Test (description = "Delete post test")
    @Severity(SeverityLevel.CRITICAL)
    public void deletePostTest(){

        Logging.logInfo("Test " + testName + " started");
        postsPage.deletePost(Post.getPostTitle());
        Assert.assertFalse(postsPage.isPostAvailable(Post.getPostTitle()), "Post still available and was not delete");
        Logging.logInfo("Test " + testName + " finished");
    }

}
