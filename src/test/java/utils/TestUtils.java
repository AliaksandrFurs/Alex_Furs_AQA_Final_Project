package utils;

import elements.rows.PagesPageTableRow;
import interfaces.pages.IPage;
import interfaces.pages.IPageWithDraft;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestUtils {

    static WebDriver driver = Browser.getDriver();

    @Step("Verify is entity presented on page")
    public static boolean isEntityAvailable(IPage page, String entityName) {
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get("table")));
        if(page.getPageTable().getRowsNumber() == 0){
            Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get("noEntityFoundLocator")));
            if(driver.findElement(page.getPageLocatorsMap().get("noEntityFoundLocator")).getText().equals("No media files found.")){
                return false;
            }
        }else{
            if(page.getPageTable().getAllRowsTitle().size() > 0){
                List<WebElement> temList = page.getPageTable().getAllRowsTitle();
                for(WebElement element : temList){
                    if(element.getText().equals(entityName)){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Step("Verify ie page entity was previously updated")
    public static boolean isEntityWasUpdate(IPage page, String entityName){
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get("table")));
        if(isEntityAvailable(page, entityName)){
            PagesPageTableRow currentRow = page.getPageTable().getRowByTitle(entityName);
            if(tempRow.getId().equals(currentRow.getId())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Step("Verisy is post is a draft")
    public static boolean isEntityDraft(IPageWithDraft page, String postTitle){
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get("pageNameLocator")));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get("table")));
        return page.getPageTable().isTitleDraft(postTitle);
    }
}
