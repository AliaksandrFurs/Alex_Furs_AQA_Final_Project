package factories;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.PostsPage;
import utils.Logging;

public class PageFactory {

    private static LoginPage loginPage;
    private static PostsPage postsPage;

    private PageFactory(){}

    public static LoginPage getLoginPage(WebDriver driver){

        if(loginPage == null){

            loginPage = new LoginPage(driver);
            Logging.logInfo("Login page created successfully");

        }
        return loginPage;
    }

    public static PostsPage getPostsPage(WebDriver driver){

        if(postsPage == null){

            postsPage = new PostsPage(driver);
            Logging.logInfo("Posts page created successfully");

        }
        return postsPage;
    }


}
