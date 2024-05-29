package pages;

import elements.interfaces.BasicOrdinaryPageActions;
import elements.interfaces.MainMenuBarActions;
import elements.interfaces.Page;
import elements.tables.MediaPageTable;
import enums.MainMenuBarSectionEnum;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Wait;

public class MediaLibraryOrdinaryPage extends BasePage implements Page, BasicOrdinaryPageActions, MainMenuBarActions {

    private final static String MEDIA_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/upload.php";

    private By mediaLocator = By.className("has-media-icon");
    private MediaPageTable mediaPageTable= new MediaPageTable(driver);
    private Select actionDropdownSelect;

    public MediaLibraryOrdinaryPage(WebDriver driver){

        super(driver);
        setPageName("Media");
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "bulk-action-selector-top")
    WebElement dropdown;

    @Override
    @Step("Open media page")
    public void openPage() {
        driver.get(MEDIA_URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        mediaPageTable.updateRowsNumber();
        mediaPageTable.createTableRows();
    }

    @Override
    @Step("Verify is media page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    @Override
    @Step("Search media entity")
    public void searchEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        mediaPageTable.deleteTableRows();
        driver.switchTo().defaultContent();
        Wait.isElementPresented(driver.findElement(mediaSearchInput));
        Wait.isElementPresented(driver.findElement(mediaSearchInput));
        driver.findElement(mediaSearchInput).sendKeys(entityName);
        driver.findElement(searchButton).click();
        driver.switchTo().defaultContent();
        mediaPageTable.createTableRows();
    }

    @Override
    @Step("Verify is media entity presented on page")
    public boolean isEntityAvailable(String entityName) {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        if(mediaPageTable.getAllRowsTitle().size() > 0){
            if(mediaPageTable.getRowByTitle(entityName).getName().equals(entityName))
                return true;
        }
        return false;
    }

    @Override
    @Step("Delete media entity")
    public void deleteEntity(String enityName) {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        if(actionDropdownSelect == null){
            actionDropdownSelect = new Select(dropdown);
        }
        searchEntity(enityName);
        mediaPageTable.selectRows();
        actionDropdownSelect.selectByValue("delete");
        driver.findElement(applyActionButton).click();
        driver.switchTo().alert().accept();
        mediaPageTable.updateRowsNumber();
        mediaPageTable.createTableRows();
    }

    @Override
    @Step("Open adding media page by clicking on button")
    public void openAddingEntityPage() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        driver.findElement(addNewEntityButton).click();
    }

    @Override
    public void clickOnEntity(String entityName) {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        searchEntity(entityName);
        if(isEntityAvailable(entityName)){
            mediaPageTable.clickOnRowTitle(entityName);
        }
    }

    @Override
    @Step("Click on top bar section")
    public BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return mainMenuBar.ClickOnBarSection(sectionName);
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }
}
