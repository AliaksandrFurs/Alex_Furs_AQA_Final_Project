import business.Post;
import factories.PageFactory;
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

    @Test
    public void addOnePostTest(){

        editingPostPage.addNewPost(Post.getPostTitle(), Post.getPostBody());
        editingPostPage.dashboardClick();
        Assert.assertEquals(postsPage.findPost(Post.getPostTitle()),Post.getPostTitle(), "No such post available");
        Logging.logInfo("Test " + testName + " finished");

    }
}
