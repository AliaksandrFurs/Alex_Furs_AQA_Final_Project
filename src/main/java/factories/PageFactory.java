package factories;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.PostsPage;

public class PageFactory {

    private static LoginPage loginPage;
    private static PostsPage postsPage;

    private PageFactory(){}

    public static LoginPage getLoginPage(WebDriver driver){

        if(loginPage == null){

            loginPage = new LoginPage(driver);

        }
        return loginPage;
    }

    public static PostsPage getPostsPage(WebDriver driver){

        if(postsPage == null){

            postsPage = new PostsPage(driver);

        }
        return postsPage;
    }


}
