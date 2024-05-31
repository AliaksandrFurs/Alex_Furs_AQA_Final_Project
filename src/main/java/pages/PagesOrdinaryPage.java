package pages;

import interfaces.BasicOrdinaryPageActions;
import interfaces.MainMenuBarActions;
import interfaces.Page;
import elements.rows.PagesPageTableRow;
import elements.tables.PagesPageTable;
import enums.MainMenuBarSectionEnum;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Wait;

public class PagesOrdinaryPage extends  BasePage implements Page, BasicOrdinaryPageActions, MainMenuBarActions {

    private final static String PAGE_UTL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php?post_type=page";

    private Select actionDropdownSelect;
    private PagesPageTable pagesPageTable = new PagesPageTable(driver);
    private PagesPageTableRow tempRow;

    @FindBy(id = "bulk-action-selector-top")
    WebElement dropdown;

    public PagesOrdinaryPage(WebDriver driver){
        super(driver);
        setPageName("Pages");
        PageFactory.initElements(driver, this);
    }

    @Override
    @Step("Open pages page")
    public void openPage() {
        driver.get(PAGE_UTL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        pagesPageTable.updateRowsNumber();
        pagesPageTable.createTableRows();
    }

    @Override
    @Step("Verify is pages page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    @Override
    @Step("Search page entity")
    public void searchEntity(String entityName) {
        pagesPageTable.deleteTableRows();
        driver.findElement(searchInput).sendKeys(entityName);
        driver.findElement(searchButton).click();
        pagesPageTable.createTableRows();
    }

    @Override
    @Step("Verify is page entity presented on page")
    public boolean isEntityAvailable(String entityName) {
        if(pagesPageTable.getAllRowsTitle().size() > 0){
            if(pagesPageTable.getRowByTitle(entityName).getName().equals(entityName));
            return true;
        }else if(pagesPageTable.getAllRowsTitle().size() == 0 || pagesPageTable.getAllRowsTitle() == null){
            Wait.isElementPresented(driver.findElement(noEntityFoundLocator));
            return false;
        }
        return false;
    }

    @Override
    @Step("Delete page entity")
    public void deleteEntity(String enityName) {
        if(actionDropdownSelect == null){
            actionDropdownSelect = new Select(dropdown);
        }
        searchEntity(enityName);
        if(isEntityAvailable(enityName)) {
            pagesPageTable.selectRows();
            actionDropdownSelect.selectByValue("trash");
            driver.findElement(applyActionButton).click();
            pagesPageTable.updateRowsNumber();
            pagesPageTable.createTableRows();
        }
    }

    @Override
    @Step("Open creating entity page by button")
    public void openAddingEntityPage() {
        driver.findElement(addNewEntityButton).click();
    }

    @Override
    @Step("Click on top bar section")
    public BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        return mainMenuBar.ClickOnBarSection(sectionName);
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

    @Override
    @Step("Click on page entity title from list")
    public void clickOnEntity(String entityName){
        searchEntity(entityName);
        if(isEntityAvailable(entityName)){
            tempRow = pagesPageTable.getRowByTitle(entityName);
            pagesPageTable.clickOnRowTitle(entityName);
        }
    }

    @Step("Verify is page is a draft")
    public boolean isPageDraft(String postTitle){
        return pagesPageTable.isTitleDraft(postTitle);
    }

    @Step("Verify ie page entity was previously updated")
    public boolean isEntityWasUpdate(String entityName){
        if(isEntityAvailable(entityName)){
            PagesPageTableRow currentRow = pagesPageTable.getRowByTitle(entityName);
            if(tempRow.getId().equals(currentRow.getId())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
