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

public class MediaLibraryOrdinaryPage extends BasePage implements IPage {

    private final static String MEDIA_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/upload.php";
    private final static String TITLE_PATTERN = "//span[contains(text(),'%s]";
    private By mediaLocator = By.className("has-media-icon");
    private Select actionDropdownSelect;

    public MediaLibraryOrdinaryPage(WebDriver driver){
        super(driver);
        setPageName("Media");
        pageLocatorsMap.put(SEARCHINPUTLOCATOR, By.id("media-search-input"));
        pageLocatorsMap.put(ADDNEWENTITYBUTTONLOCATOR,By.xpath("//a[contains(@class, 'page-title-action')]"));
        pageLocatorsMap.put(APPLYACTIONBUTTONLOCATOR,By.id("doaction"));
        pageLocatorsMap.put(SEARCHBUTTONLOCATOR,By.id("search-submit"));
        pageLocatorsMap.put(DELETEACTIONSDROPDOWNLOCATOR, By.id("bulk-action-selector-top"));
        pageLocatorsMap.put(TABLELOCATOR, By.xpath("//table[@class='wp-list-table widefat fixed striped table-view-list media']"));
        pageLocatorsMap.put(TITLELOCATOR, By.xpath("//tbody[@id='the-list']/tr//strong/a"));
        pageLocatorsMap.put(AUTHORNAMELOCATOR, By.xpath("//tbody[@id='the-list']/tr/td[@class='author column-author']/a"));
        pageLocatorsMap.put(DATELOCATOR, By.xpath("//tbody[@id='the-list']/tr/td[@class='date column-date']"));
    }

    @Override
    @Step("Open media page")
    public void openPage() {
        driver.get(MEDIA_URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(LocatorsEnum.PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
    }

    @Override
    @Step("Verify is media page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        return true;
    }

    @Override
    @Step("Search media entity")
    public void searchEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(SEARCHINPUTLOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(SEARCHBUTTONLOCATOR)));
        PageActions.searchEntity(entityName, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
    }

    @Override
    @Step("Delete media entity")
    public void deleteEntity(String enityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        PageActions.searchEntity(enityName, pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        List<WebElement> allId = driver.findElements(pageLocatorsMap.get(ROWIDLOCATOR));
        if(allId.size() != 0){
            PageActions.deleteEntity(actionDropdownSelect, DeleteActionsEnum.DELETE, pageLocatorsMap, driver);
        }
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
    }

    @Override
    @Step("Open adding media page by clicking on button")
    public void openAddingEntityPage() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(ADDNEWENTITYBUTTONLOCATOR)));
        driver.findElement(pageLocatorsMap.get(ADDNEWENTITYBUTTONLOCATOR)).click();
    }

    @Override
    public void clickOnEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        PageActions.searchEntity(entityName,  pageLocatorsMap, driver);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(TABLELOCATOR)));
        List<WebElement> allId = driver.findElements(pageLocatorsMap.get(ROWIDLOCATOR));
        if(allId.size() != 0){
            String xpath = String.format(TITLE_PATTERN, entityName);
            driver.findElement(By.xpath(xpath)).click();
        }
    }

    @Override
    @Step("Click on top bar section")
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
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
}
