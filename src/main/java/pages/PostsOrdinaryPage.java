package pages;

import elements.interfaces.BasicOrdinaryPageActions;
import elements.interfaces.Page;
import elements.tables.PostsPageTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Wait;

public class PostsOrdinaryPage extends BasePage implements Page, BasicOrdinaryPageActions {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";

    private Select actionDropdownSelect;
    private PostsPageTable postsPageTable = new PostsPageTable(driver);

    @FindBy(id = "bulk-action-selector-top")
    WebElement dropdown;

    public PostsOrdinaryPage(WebDriver driver) {
        super(driver);
        setPageName("Posts");
        PageFactory.initElements(driver, this);
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

    public boolean isPostDraft(String postTitle){
        return postsPageTable.isTitleDraft(postTitle);
    }

    @Override
    public void searchEntity(String entityName) {
        postsPageTable.deleteTableRows();
        driver.findElement(searchInput).sendKeys(entityName);
        driver.findElement(searchButton).click();
        postsPageTable.createTableRows();
    }

    @Override
    public boolean isEntityAvailable() {
        if(postsPageTable.getAllRowsTitle().size()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void deleteEntity(String postName) {
        if(actionDropdownSelect == null){
            actionDropdownSelect = new Select(dropdown);
        }
        searchEntity(postName);
        postsPageTable.selectRows();
        actionDropdownSelect.selectByValue("trash");
        driver.findElement(applyActionButton).click();
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
    }

    @Override
    public void openAddingEntityPage() {
        driver.findElement(addNewEntityButton).click();
    }
}
