package pages;

import enums.MainMenuBarSectionEnum;
import interfaces.pages.IPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.PageActions;
import utils.Wait;

import java.util.HashMap;
import java.util.List;

public class PostsOrdinaryPage extends BasePage implements IPage {


    private final static String POSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";
    private static final String TITLE_PATTERN = "//a[contains(text(), '%s')]";
    private Select actionDropdownSelect;

    public PostsOrdinaryPage(WebDriver driver) {
        super(driver);
        setPageName("Posts");
        pageLocatorsMap.put("searchInput", By.id("post-search-input"));
        pageLocatorsMap.put("addNewEntityButton",By.xpath("//a[contains(@class, 'page-title-action')]"));
        pageLocatorsMap.put("applyActionButton",By.id("doaction"));
        pageLocatorsMap.put("searchButton",By.id("search-submit"));
        pageLocatorsMap.put("dropdown", By.id("bulk-action-selector-top"));
        pageLocatorsMap.put("table", By.className("wp-list-table widefat fixed striped table-view-list posts"));
        pageLocatorsMap.put("title", By.xpath("//tbody[@id='the-list']/tr//strong/a"));
        pageLocatorsMap.put("authorName", By.xpath("//tbody[@id='the-list']/tr/td[@class='author column-author']/a"));
        pageLocatorsMap.put("date", By.xpath("//tbody[@id='the-list']/tr/td[@class='date column-date']"));
    }

    @Override
    @Step("Open posts page")
    public void openPage() {
        driver.get(POSTS_URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
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
        PageActions.searchEntity(entityName, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
    }

    @Override
    @Step("Delete post entity")
    public void deleteEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        PageActions.searchEntity(entityName, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        List<WebElement> allId = driver.findElements(pageLocatorsMap.get("rowId"));
        if(allId.size() != 0){
            PageActions.deleteEntity(actionDropdownSelect, "trash", pageLocatorsMap, driver);
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
        PageActions.searchEntity(entityName, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("table")));
        List<WebElement> allId = driver.findElements(pageLocatorsMap.get("rowId"));
        if(allId.size() != 0){
            String xpath = String.format(TITLE_PATTERN, entityName);
            driver.findElement(By.xpath(xpath)).click();
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
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

}
