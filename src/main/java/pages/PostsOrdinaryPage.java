package pages;

import elements.interfaces.BasicOrdinaryPageActions;
import elements.interfaces.MainMenuBarActions;
import elements.interfaces.Page;
import elements.rows.PostPageTableRow;
import elements.tables.PostsPageTable;
import enums.MainMenuBarSectionEnum;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Wait;

public class PostsOrdinaryPage extends BasePage implements Page, BasicOrdinaryPageActions, MainMenuBarActions {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";

    private Select actionDropdownSelect;
    private PostsPageTable postsPageTable = new PostsPageTable(driver);
    private PostPageTableRow tempRow;

    @FindBy(id = "bulk-action-selector-top")
    WebElement dropdown;

    public PostsOrdinaryPage(WebDriver driver) {
        super(driver);
        setPageName("Posts");
        PageFactory.initElements(driver, this);
    }

    @Override
    @Step("Open posts page")
    public void openPage() {
        driver.get(POSTS_URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
    }

    @Override
    @Step("Verify is posts page is succcessfully opened")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    @Override
    @Step("Search post")
    public void searchEntity(String entityName) {
        postsPageTable.deleteTableRows();
        driver.findElement(searchInput).sendKeys(entityName);
        driver.findElement(searchButton).click();
        postsPageTable.createTableRows();
    }

    @Override
    @Step("Verify is post is presented on a page")
    public boolean isEntityAvailable(String entityName) {
        if(postsPageTable.getAllRowsTitle().size() > 0){
            if(postsPageTable.getRowByTitle(entityName).getName().equals(entityName))
                return true;
        }else if(postsPageTable.getAllRowsTitle().size() == 0 || postsPageTable.getAllRowsTitle() == null){
            Wait.isElementPresented(driver.findElement(noEntityFoundLocator));
            return false;
        }
        return false;
    }

    @Override
    @Step("Delete post entity")
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
    @Step("Open creating entity page by button")
    public void openAddingEntityPage() {
        driver.findElement(addNewEntityButton).click();
    }

    @Override
    @Step("Click on post entity title from list")
    public void clickOnEntity(String entityName) {
        searchEntity(entityName);
        if(isEntityAvailable(entityName)){
            tempRow = postsPageTable.getRowByTitle(entityName);
            postsPageTable.clickOnRowTitle(entityName);
        }
    }

    @Override
    @Step("C;ick on main menu bar section")
    public BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        return mainMenuBar.ClickOnBarSection(sectionName);
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

    @Step("Verify is post entity was previously updated")
    public boolean isEntityWasUpdate(String entityName){
        if(isEntityAvailable(entityName)){
            PostPageTableRow currentRow = postsPageTable.getRowByTitle(entityName);
            if(tempRow.getId().equals(currentRow.getId())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Step("Verisy is post is a draft")
    public boolean isPostDraft(String postTitle){
        return postsPageTable.isTitleDraft(postTitle);
    }
}
