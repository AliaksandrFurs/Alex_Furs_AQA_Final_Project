package tests;

import business.Post;
import enums.MainMenuBarSectionEnum;
import factories.PageFactory;
import interfaces.pages.ICreatePageInterface;
import interfaces.pages.ILoginPageInterface;
import interfaces.pages.IPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.AllureReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Configuration;
import utils.Logging;
import utils.TestUtils;

@Listeners({AllureReportListener.class})
public class PostsPageTest extends BaseTest {

    IPage postsPage = PageFactory.getPostsPage(driver);
    ILoginPageInterface loginPage = PageFactory.getLoginPage(driver);
    ICreatePageInterface createPage = PageFactory.getCreatePage(driver);

    @BeforeClass(alwaysRun = true)
    public void navigateToAndPrepareData(){
        if(isUserLogin == false){
            loginPage.openPage();
            loginPage.doLogin(Configuration.getLogin(), Configuration.getPassword(), false);
            isUserLogin = true;
        }
        postsPage.ClickOnBarSection(MainMenuBarSectionEnum.POSTS);
        Logging.logInfo("Posts page opened successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void returnToMainPage(){
        postsPage.ClickOnBarSection(MainMenuBarSectionEnum.POSTS);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        postsPage.deleteEntity("Test as draft");
        postsPage.ClickOnBarSection(MainMenuBarSectionEnum.POSTS);
    }

    @Test (priority = 1, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Adding one single post test")
    public void addOnePostTest(){
        postsPage.openAddingEntityPage();
        createPage.addNewEntity(Post.getPostTitle(), Post.getPostBody());
        postsPage.searchEntity(Post.getPostTitle());
        Assert.assertTrue(TestUtils.isEntityAvailable(postsPage, Post.getPostTitle()), "Post was not added");
    }

    @Test (priority = 2, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Find specific post")
    public void findPostTest(){
        postsPage.searchEntity(Post.getPostTitle());
        Assert.assertTrue(TestUtils.isEntityAvailable(postsPage, Post.getPostTitle()), "Post does not found");
    }

    @Test (priority = 5, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Delete post test")
    public void deletePostTest(){
        postsPage.deleteEntity(Post.getNewPostTitle());
        Assert.assertFalse(TestUtils.isEntityAvailable(postsPage, Post.getNewPostTitle()), "Post still available and was not delete");
    }

    @Test (priority = 3, groups = "regression")
    @Severity(SeverityLevel.NORMAL) @Description("Save post as draft")
    public void addPostDraftTest(){
        postsPage.openAddingEntityPage();
        createPage.saveEntityAsDraft("Test as draft", "Test as draft");
        postsPage.searchEntity("Test as draft");
        Assert.assertTrue(TestUtils.isEntityDraft(postsPage,"Test as draft"), "Post is not draft");
    }

    @Test(priority = 4, groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL) @Description("Edit existing post")
    public void editPostTest(){
        postsPage.clickOnEntity(Post.getPostTitle());
        createPage.updateEntity(Post.getNewPostTitle(), Post.getNewPostBody());
        postsPage.searchEntity(Post.getNewPostTitle());
        Assert.assertTrue(TestUtils.isEntityWasUpdate(postsPage, Post.getNewPostTitle()), "Post was not edited");
    }
}
