package pages;

import elements.tables.MediaPageTable;
import enums.MainMenuBarSectionEnum;
import interfaces.pages.IPage;
import interfaces.tables.ITable;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.PageActions;
import utils.Wait;

import java.util.HashMap;

public class MediaLibraryOrdinaryPage extends BasePage implements IPage {

    private final static String MEDIA_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/upload.php";

    private By mediaLocator = By.className("has-media-icon");
    private ITable mediaPageTable= new MediaPageTable(driver);
    private Select actionDropdownSelect;

    public MediaLibraryOrdinaryPage(WebDriver driver){
        super(driver);
        setPageName("Media");
        pageLocatorsMap.put("searchInput", By.id("media-search-input"));
        pageLocatorsMap.put("addNewEntityButton",By.xpath("//a[contains(@class, 'page-title-action')]"));
        pageLocatorsMap.put("applyActionButton",By.id("doaction"));
        pageLocatorsMap.put("searchButton",By.id("search-submit"));
        pageLocatorsMap.put("dropdown", By.id("bulk-action-selector-top"));
        pageLocatorsMap.put("table", By.xpath("//table[@class='wp-list-table widefat fixed striped table-view-list media']"));
    }

    @Override
    @Step("Open media page")
    public void openPage() {
        driver.get(MEDIA_URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        mediaPageTable.deleteTableRows();
        mediaPageTable.updateRowsNumber();
        mediaPageTable.createTableRows();
    }

    @Override
    @Step("Verify is media page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        return true;
    }

    @Override
    @Step("Search media entity")
    public void searchEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("searchInput")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("searchButton")));
        PageActions.searchEntity(entityName, mediaPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        mediaPageTable.deleteTableRows();
        mediaPageTable.updateRowsNumber();
        mediaPageTable.createTableRows();
    }

    @Override
    @Step("Delete media entity")
    public void deleteEntity(String enityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        PageActions.searchEntity(enityName, mediaPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        mediaPageTable.deleteTableRows();
        mediaPageTable.updateRowsNumber();
        mediaPageTable.createTableRows();
        if(mediaPageTable.getRowsNumber() != 0){
            PageActions.deleteEntity(actionDropdownSelect, "delete", mediaPageTable, pageLocatorsMap, driver);
        }
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
    }

    @Override
    @Step("Open adding media page by clicking on button")
    public void openAddingEntityPage() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("addNewEntityButton")));
        driver.findElement(pageLocatorsMap.get("addNewEntityButton")).click();
    }

    @Override
    public void clickOnEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        PageActions.searchEntity(entityName, mediaPageTable, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        mediaPageTable.deleteTableRows();
        mediaPageTable.updateRowsNumber();
        mediaPageTable.createTableRows();
        if(mediaPageTable.getRowsNumber() != 0){
            if (actionDropdownSelect == null) {
                actionDropdownSelect = new Select(driver.findElement(pageLocatorsMap.get("dropdown")));
            }
            mediaPageTable.clickOnRowTitle(entityName);
        }
    }

    @Override
    @Step("Click on top bar section")
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        PageActions.ClickOnBarSection(sectionName, mainMenuBar);
    }

    @Override
    public HashMap<String, By> getPageLocatorsMap() {
        return pageLocatorsMap;
    }

    @Override
    public ITable getPageTable() {
        return mediaPageTable;
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }
}
