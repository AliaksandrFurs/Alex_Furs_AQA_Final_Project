import factories.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PostsPage;
import utils.Configuration;
import utils.Logging;

public class PostsPageTest extends BaseTest {

    //class to be determinated
    PostsPage postsPage = PageFactory.getPostsPage(driver);
    LoginPage loginPage = PageFactory.getLoginPage(driver);

    @BeforeClass
    public void navigateTo(){

        loginPage.openPage();
        loginPage.doRememberMeLogin(Configuration.getLogin(), Configuration.getPassword());
        postsPage.openPage();
        Logging.logInfo("Posts page opened successfully");
    }

    //veifying is rows created successfull
    @Test
    public void firstTest(){


    }
}
