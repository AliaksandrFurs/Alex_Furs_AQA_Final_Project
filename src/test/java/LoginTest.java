import factories.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PostsPage;
import utils.Configuration;

public class LoginTest extends BaseTest{

    LoginPage loginPage = PageFactory.getLoginPage(driver);


    @BeforeClass
    public void navigateTo(){

        loginPage.openPage();

    }

    @Test
    public void successfullLoginTest(){

        loginPage.doOrdinaryLogin(Configuration.getLogin(), Configuration.getPassword());
    }


}
