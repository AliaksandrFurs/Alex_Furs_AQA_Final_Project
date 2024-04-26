package factories;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class PageFactory {

    private static LoginPage loginPage;

    private PageFactory(){}

    public static LoginPage getLoginPage(WebDriver driver){

        if(loginPage == null){

            loginPage = new LoginPage(driver);

        }
        return loginPage;
    }


}
