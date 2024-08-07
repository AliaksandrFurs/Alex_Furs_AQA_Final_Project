package pages;

import enums.DeleteActionsEnum;
import enums.LocatorsEnum;
import enums.MainMenuBarSectionEnum;
import interfaces.pages.IPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.PageActions;
import utils.Wait;
import static enums.LocatorsEnum.*;
import java.util.HashMap;
import java.util.List;

public class PagesOrdinaryPage extends  BasePage implements IPage {

    private final static String PAGE_UTL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php?post_type=page";
    private static final String TITLE_PATTERN = "//a[contains(text(), '%s')]";
    private Select actionDropdownSelect;

    public PagesOrdinaryPage(WebDriver driver){
        super(driver);
        setPageName("Pages");
        pageLocatorsMap.put(SEARCHINPUTLOCATOR, By.id("post-search-input"));
        pageLocatorsMap.put(ADDNEWENTITYBUTTONLOCATOR,By.xpath("//a[contains(@class, 'page-title-action')]"));
        pageLocatorsMap.put(APPLYACTIONBUTTONLOCATOR,By.id("doaction"));
        pageLocatorsMap.put(SEARCHBUTTONLOCATOR,By.id("search-submit"));
        pageLocatorsMap.put(DELETEACTIONSDROPDOWNLOCATOR, By.id("bulk-action-selector-top"));
        pageLocatorsMap.put(TABLELOCATOR, By.className("wp-list-table widefat fixed striped table-view-list pages"));
        pageLocatorsMap.put(DRAFTLOCATOR, By.xpath("//strong/span[contains(text(), 'Draft')]"));
        pageLocatorsMap.put(TITLELOCATOR, By.xpath("//tbody[@id='the-list']/tr//strong/a"));
        pageLocatorsMap.put(AUTHORNAMELOCATOR, By.xpath("//tbody[@id='the-list']/tr/td[@class='author column-author']/a"));
        pageLocatorsMap.put(DATELOCATOR, By.xpath("//tbody[@id='the-list']/tr/td[@class='date column-date']"));
        pageLocatorsMap.put(COMMENTLOCATOR, By.xpath("//tbody[@id='the-list']/tr/td[@class='comments column-comments']/div//span[@class='screen-reader-text']"));
    }

    @Override
    @Step("Open pages page")
    public void openPage() {
        driver.get(PAGE_UTL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
    }

    @Override
    @Step("Verify is pages page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        return true;
    }

    @Override
    @Step("Search page entity")
    public void searchEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("searchInput")));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("searchButton")));
        PageActions.searchEntity(entityName, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
    }

    @Override
    @Step("Delete page entity")
    public void deleteEntity(String enityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        PageActions.searchEntity(enityName,  pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        List<WebElement> allId = driver.findElements(pageLocatorsMap.get(ROWIDLOCATOR));
        if(allId.size() != 0){
            PageActions.deleteEntity(actionDropdownSelect, DeleteActionsEnum.TRASH, pageLocatorsMap, driver);
        }
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
    }

    @Override
    @Step("Open creating entity page by button")
    public void openAddingEntityPage() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(ADDNEWENTITYBUTTONLOCATOR)));
        driver.findElement(pageLocatorsMap.get(ADDNEWENTITYBUTTONLOCATOR)).click();
    }

    @Override
    @Step("Click on top bar section")
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        PageActions.ClickOnBarSection(sectionName, mainMenuBar);
    }

    @Override
    public HashMap<LocatorsEnum, By> getPageLocatorsMap() {
        return pageLocatorsMap;
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

    @Override
    @Step("Click on page entity title from list")
    public void clickOnEntity(String entityName){
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        PageActions.searchEntity(entityName, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        List<WebElement> allId = driver.findElements(pageLocatorsMap.get(ROWIDLOCATOR));
        if(allId.size() != 0){
            String xpath = String.format(TITLE_PATTERN, entityName);
            driver.findElement(By.xpath(xpath)).click();
        }
    }
}
