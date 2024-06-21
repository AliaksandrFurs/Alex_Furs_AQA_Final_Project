package pages;

import elements.rows.PostPageTableRow;
import elements.tables.PostsPageTable;
import enums.MainMenuBarSectionEnum;
import interfaces.pages.IPageWithDraft;
import interfaces.tables.ITableWithDraft;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.PageActions;
import utils.Wait;

import java.util.HashMap;

public class PostsOrdinaryPage extends BasePage implements IPageWithDraft {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";

    private Select actionDropdownSelect;
    private ITableWithDraft postsPageTable = new PostsPageTable(driver);

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
        postsPageTable.deleteTableRows();
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
    }

    @Override
    @Step("Delete post entity")
    public void deleteEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        PageActions.searchEntity(entityName, postsPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        postsPageTable.deleteTableRows();
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
        if(postsPageTable.getRowsNumber() != 0){
            PageActions.deleteEntity(actionDropdownSelect, "trash", postsPageTable, pageLocatorsMap, driver);
        }
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
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
        PageActions.searchEntity(entityName, postsPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        postsPageTable.deleteTableRows();
        postsPageTable.updateRowsNumber();
        postsPageTable.createTableRows();
        if(postsPageTable.getRowsNumber() != 0){
            if (actionDropdownSelect == null) {
                actionDropdownSelect = new Select(driver.findElement(pageLocatorsMap.get("dropdown")));
            }
            postsPageTable.clickOnRowTitle(entityName);
        }
    }

    @Override
    @Step("C;ick on main menu bar section")
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        PageActions.ClickOnBarSection(sectionName, mainMenuBar);
    }

    @Override
    public HashMap<String, By> getPageLocatorsMap() {
        return pageLocatorsMap;
    }

    @Override
    public ITableWithDraft getPageTable() {
        return postsPageTable;
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

}
