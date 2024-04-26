import factories.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Configuration;

public class LoginTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
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
