import factories.PageFactory;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Configuration;
import utils.Logging;

public class LoginTest extends BaseTest{

    LoginPage loginPage = PageFactory.getLoginPage(driver);



    @BeforeClass
    public void navigateTo(){

        loginPage.openPage();
        Logging.logInfo("Login page opened successfully");

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void successfullLoginTest(){

        loginPage.doOrdinaryLogin(Configuration.getLogin(), Configuration.getPassword());
        Logging.logInfo("Login successfull");
    }


}
