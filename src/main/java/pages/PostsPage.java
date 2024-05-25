package pages;

import elements.interfaces.Page;
import elements.tables.PostsPageTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Wait;

public class PostsPage extends BasePage implements Page {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";
    private By addPageButtonLocator = By.xpath("//a[@class = 'page-title-action']");
    private By searchInput = By.id("post-search-input");
    private By searchButton = By.id("search-submit");
    private By applyActionButton = By.id("doaction");
    private Select actionDropdownSelect;

    @FindBy(id = "bulk-action-selector-top")
    WebElement dropdown;

    private PostsPageTable postsPageTable = new PostsPageTable(driver);

    public PostsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
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

    public void findPost(String postName){

        postsPageTable.deleteTableRows();
        driver.findElement(searchInput).sendKeys(postName);
        driver.findElement(searchButton).click();
        postsPageTable.createTableRows();

    }

    public void deletePost(String postName){

        if(actionDropdownSelect == null){
            actionDropdownSelect = new Select(dropdown);
        }
        findPost(postName);
        postsPageTable.selectRows();
        actionDropdownSelect.selectByValue("trash");
        driver.findElement(applyActionButton).click();
    }

    public boolean isPostAvailable(String postTitle){

        if(postsPageTable.getAllRowsTitle().size()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPostDraft(String postTitle){

        //findPost(postTitle);
        return postsPageTable.isTitleDraft(postTitle);
    }
}
