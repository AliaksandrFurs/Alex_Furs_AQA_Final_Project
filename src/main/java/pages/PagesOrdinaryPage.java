package pages;

import elements.rows.PagesPageTableRow;
import elements.tables.PagesPageTable;
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

public class PagesOrdinaryPage extends  BasePage implements IPageWithDraft {

    private final static String PAGE_UTL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php?post_type=page";

    private Select actionDropdownSelect;
    private ITableWithDraft pagesPageTable = new PagesPageTable(driver);

    public PagesOrdinaryPage(WebDriver driver){
        super(driver);
        setPageName("Pages");
        pageLocatorsMap.put("searchInput", By.id("post-search-input"));
        pageLocatorsMap.put("addNewEntityButton",By.xpath("//a[contains(@class, 'page-title-action')]"));
        pageLocatorsMap.put("applyActionButton",By.id("doaction"));
        pageLocatorsMap.put("searchButton",By.id("search-submit"));
        pageLocatorsMap.put("dropdown", By.id("bulk-action-selector-top"));
        pageLocatorsMap.put("table", By.className("wp-list-table widefat fixed striped table-view-list pages"));
    }

    @Override
    @Step("Open pages page")
    public void openPage() {
        driver.get(PAGE_UTL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        pagesPageTable.updateRowsNumber();
        pagesPageTable.createTableRows();
    }

    @Override
    @Step("Verify is pages page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        return true;
    }

    @Override
    @Step("Search page entity")
    public void searchEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("searchInput")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("searchButton")));
        PageActions.searchEntity(entityName, pagesPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        pagesPageTable.deleteTableRows();
        pagesPageTable.updateRowsNumber();
        pagesPageTable.createTableRows();
    }

    @Override
    @Step("Delete page entity")
    public void deleteEntity(String enityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        PageActions.searchEntity(enityName, pagesPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        pagesPageTable.deleteTableRows();
        pagesPageTable.updateRowsNumber();
        pagesPageTable.createTableRows();
        if(pagesPageTable.getRowsNumber() != 0){
            PageActions.deleteEntity(actionDropdownSelect, "trash", pagesPageTable, pageLocatorsMap, driver);
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
    @Step("Click on top bar section")
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        PageActions.ClickOnBarSection(sectionName, mainMenuBar);
    }

    @Override
    public HashMap<String, By> getPageLocatorsMap() {
        return pageLocatorsMap;
    }

    @Override
    public ITableWithDraft getPageTable() {
        return pagesPageTable;
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

    @Override
    @Step("Click on page entity title from list")
    public void clickOnEntity(String entityName){
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        PageActions.searchEntity(entityName, pagesPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        pagesPageTable.deleteTableRows();
        pagesPageTable.updateRowsNumber();
        pagesPageTable.createTableRows();
        if(pagesPageTable.getRowsNumber() != 0){
            if (actionDropdownSelect == null) {
                actionDropdownSelect = new Select(driver.findElement(pageLocatorsMap.get("dropdown")));
            }
            pagesPageTable.clickOnRowTitle(entityName);
        }
    }
}
