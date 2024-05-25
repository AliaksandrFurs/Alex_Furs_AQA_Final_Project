package pages;

import elements.interfaces.BasicOrdinaryPageActions;
import elements.interfaces.MainMenuBarActions;
import elements.interfaces.Page;
import elements.tables.MediaPageTable;
import enums.MainMenuBarSectionEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Wait;

public class MediaLibraryOrdinaryPage extends BasePage implements Page, BasicOrdinaryPageActions, MainMenuBarActions {

    private final static String MEDIA_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/upload.php";

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
    public void openPage() {
        driver.get(MEDIA_URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        mediaPageTable.updateRowsNumber();
        mediaPageTable.createTableRows();
    }

    @Override
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    @Override
    public void searchEntity(String entityName) {
        mediaPageTable.deleteTableRows();
        driver.findElement(mediaSearchInput).sendKeys(entityName);
        driver.findElement(searchButton).click();
        mediaPageTable.createTableRows();
    }

    @Override
    public boolean isEntityAvailable() {
        if(mediaPageTable.getAllRowsTitle().size() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void deleteEntity(String enityName) {
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
    public void openAddingEntityPage() {
        driver.findElement(addNewEntityButton).click();
    }

    @Override
    public BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        return mainMenuBar.ClickOnBarSection(sectionName);
    }

    @Override
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }
}
