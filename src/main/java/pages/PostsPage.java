package pages;

import elements.interfaces.Page;
import elements.tables.PostsPageTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Wait;

public class PostsPage extends BasePage implements Page {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";
    private By addPageButtonLocator = By.xpath("//a[@class = 'page-title-action']");
    private By searchInput = By.id("post-search-input");
    private By searchButton = By.id("search-submit");

    private PostsPageTable postsPageTable = new PostsPageTable(driver);

    public PostsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {

        driver.get(POSTS_URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
    }

    @Override
    public boolean isOpened() {

        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    public void clickOnAddNewPostButton(){

        driver.findElement(addPageButtonLocator).click();
    }

    public String findPost(String postName){

        postsPageTable.deleteTableRows();
        driver.findElement(searchInput).sendKeys(postName);
        driver.findElement(searchButton).click();
        postsPageTable.createTableRows();
        return postsPageTable.getAllRowsTitle().get(0).getText();

    }
}
