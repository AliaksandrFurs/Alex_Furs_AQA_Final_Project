import factories.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EditingPostPage;
import pages.LoginPage;
import pages.PostsPage;
import utils.Configuration;
import utils.Logging;

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


    @Test
    public void findPostTest(){

        //TBD
        Assert.assertEquals(postsPage.findPost("555"), "555");
        Logging.logInfo("Post find successfully");

    }

}
