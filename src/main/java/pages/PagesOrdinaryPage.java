package pages;

import elements.interfaces.BasicOrdinaryPageActions;
import elements.interfaces.MainMenuBarActions;
import elements.interfaces.Page;
import elements.tables.PagesPageTable;
import enums.MainMenuBarSectionEnum;
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

    @FindBy(id = "bulk-action-selector-top")
    WebElement dropdown;

    public PagesOrdinaryPage(WebDriver driver){
        super(driver);
        setPageName("Pages");
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() {
        driver.get(PAGE_UTL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        pagesPageTable.updateRowsNumber();
        pagesPageTable.createTableRows();
    }

    @Override
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    @Override
    public void searchEntity(String entityName) {
        pagesPageTable.deleteTableRows();
        driver.findElement(searchInput).sendKeys(entityName);
        driver.findElement(searchButton).click();
        pagesPageTable.createTableRows();
    }

    @Override
    public boolean isEntityAvailable() {
        if(pagesPageTable.getAllRowsTitle().size() > 0){
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
        pagesPageTable.selectRows();
        actionDropdownSelect.selectByValue("trash");
        driver.findElement(applyActionButton).click();
        pagesPageTable.updateRowsNumber();
        pagesPageTable.createTableRows();
    }

    @Override
    public void openAddingEntityPage() {
        driver.findElement(addNewEntityButton).click();
    }

    public boolean isPageDraft(String postTitle){

        //findPost(postTitle);
        return pagesPageTable.isTitleDraft(postTitle);
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
