package factories;

import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Logging;

public class PageFactory {

    private static LoginPage loginPage;
    private static PostsOrdinaryPage postsPage;
    private static CreatePage createPage;
    private static MainPage mainPage;
    private static MediaLibraryOrdinaryPage mediaPage;
    private static PagesOrdinaryPage pagesPage;
    private static UploadNewMediaPage uploadNewMediaPage;

    private PageFactory(){}

    public static LoginPage getLoginPage(WebDriver driver){
        if(loginPage == null){
            loginPage = new LoginPage(driver);
            Logging.logInfo("Login page created successfully");
        }
        return loginPage;
    }

    public static PostsOrdinaryPage getPostsPage(WebDriver driver){
        if(postsPage == null){
                postsPage = new PostsOrdinaryPage(driver);
                Logging.logInfo("Posts page created successfully");
        }
        return postsPage;
    }


    public static CreatePage getCreatePage(WebDriver driver){
        if(createPage == null){
            createPage = new CreatePage(driver);
            Logging.logInfo("Editing posts page created successfully");
        }
        return createPage;
    }

    public static MainPage getMainPage(WebDriver driver){
            mainPage = new MainPage(driver);
            Logging.logInfo("Main page created successfully");
        return mainPage;
    }

    public static MediaLibraryOrdinaryPage getMediaPage(WebDriver driver){
        if(mediaPage == null){
                mediaPage = new MediaLibraryOrdinaryPage(driver);
                Logging.logInfo("Media page created successfully");
        }
        return mediaPage;
    }

    public static PagesOrdinaryPage getPagesPage(WebDriver driver){
        if(pagesPage == null){
                pagesPage = new PagesOrdinaryPage(driver);
                Logging.logInfo("Pages page created successfully");
        }
        return pagesPage;
    }

    public static UploadNewMediaPage getUploadNewMediaPage(WebDriver driver){
        if(uploadNewMediaPage == null){
            uploadNewMediaPage = new UploadNewMediaPage(driver);
            Logging.logInfo("Upload new media page created successfully");
        }
        return uploadNewMediaPage;
    }
}
