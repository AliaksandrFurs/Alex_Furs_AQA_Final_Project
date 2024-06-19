package pages;

import interfaces.pages.IPostsOrdinaryPageInterface;
import elements.rows.PostPageTableRow;
import elements.tables.PostsPageTable;
import enums.MainMenuBarSectionEnum;
import interfaces.tables.IPostsPageTableInterface;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.PageActions;
import utils.Wait;

public class PostsOrdinaryPage extends BasePage implements IPostsOrdinaryPageInterface {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";

    private Select actionDropdownSelect;
    private IPostsPageTableInterface postsPageTable = new PostsPageTable(driver);
    private PostPageTableRow tempRow;

    public PostsOrdinaryPage(WebDriver driver) {
        super(driver);
        setPageName("Posts");
        pageLocatorsMap.put("searchInput", By.id("post-search-input"));
        pageLocatorsMap.put("addNewEntityButton",By.xpath("//a[contains(@class, 'page-title-action')]"));
        pageLocatorsMap.put("applyActionButton",By.id("doaction"));
        pageLocatorsMap.put("searchButton",By.id("search-submit"));
        pageLocatorsMap.put("dropdown", By.id("bulk-action-selector-top"));
        pageLocatorsMap.put("table", By.className("wp-list-table widefat fixed striped table-view-list posts"));
    }

    @Override
    @Step("Open posts page")
    public void openPage() {
        driver.get(POSTS_URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
    }

    @Override
    @Step("Verify is posts page is succcessfully opened")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        return true;
    }

    @Override
    @Step("Search post")
    public void searchEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("mediaSearchInput")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("searchButton")));
        PageActions.searchEntity(entityName, postsPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        if(isEntityAvailable(entityName)){
            postsPageTable.createTableRows();
        }
    }

    @Override
    @Step("Verify is post is presented on a page")
    public boolean isEntityAvailable(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        if(postsPageTable.getAllRowsTitle().size() > 0){
            if(postsPageTable.getRowByTitle(entityName).getName().equals(entityName))
                return true;
        }else if(postsPageTable.getAllRowsTitle().size() == 0 || postsPageTable.getAllRowsTitle() == null ||
                driver.findElement(pageLocatorsMap.get("noEntityFoundLocator")).getText().equals("No posts found.")){
            Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("noEntityFoundLocator")));
            return false;
        }
        return false;
    }

    @Override
    @Step("Delete post entity")
    public void deleteEntity(String postName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        if(actionDropdownSelect == null){
            actionDropdownSelect = new Select(driver.findElement(pageLocatorsMap.get("dropdown")));
        }
        PageActions.searchEntity(postName, postsPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        if(isEntityAvailable(postName)){
            PageActions.deleteEntity(actionDropdownSelect, "trash", postsPageTable, pageLocatorsMap, driver);
        }
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
    }

    @Override
    @Step("Open creating entity page by button")
    public void openAddingEntityPage() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("addNewEntityButton")));
        driver.findElement(pageLocatorsMap.get("addNewEntityButton")).click();
    }

    @Override
    @Step("Click on post entity title from list")
    public void clickOnEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        searchEntity(entityName);
        if(isEntityAvailable(entityName)){
            tempRow = postsPageTable.getRowByTitle(entityName);
            postsPageTable.clickOnRowTitle(entityName);
        }
    }

    @Override
    @Step("C;ick on main menu bar section")
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        PageActions.ClickOnBarSection(sectionName, mainMenuBar);
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

    @Step("Verify is post entity was previously updated")
    public boolean isEntityWasUpdate(String entityName){
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
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
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        return postsPageTable.isTitleDraft(postTitle);
    }
}
