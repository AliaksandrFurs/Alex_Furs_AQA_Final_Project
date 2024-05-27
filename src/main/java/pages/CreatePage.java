package pages;

import elements.interfaces.BasicCreatePageActions;
import enums.AddingEntityTypeEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Browser;
import utils.Wait;

public class CreatePage implements BasicCreatePageActions {


    WebDriver driver = Browser.getDriver();
    private final static String PAGE_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/post-new.php?post_type=page";
    private final static String POST_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/post-new.php";

    @FindBy(xpath = "//div//iframe[@name='editor-canvas']")
    WebElement iframe;

    public CreatePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @Override
    public void openPage(AddingEntityTypeEnum entityType) {
        switch(entityType){
            case PAGE -> driver.get(PAGE_URL);
            case POST -> driver.get(POST_URL);
        }
        driver.switchTo().frame(iframe);
        Wait.isElementPresented(driver.findElement(addTitleField));
    }

    @Override
    public boolean isOpened() {
        driver.switchTo().frame(iframe);
        Wait.isElementPresented(driver.findElement(addTitleField));
        return true;
    }

    @Override
    public void dashboardClick() {
        driver.findElement(dashboardLogo).click();
    }

    @Override
    public void addNewEntity(String postTitle, String postBody) {
        driver.switchTo().frame(iframe);
        //driver.findElement(addTitleField).click();
        driver.findElement(addTitleField).sendKeys(postTitle);
        driver.findElement(addPostBodyField).sendKeys(postBody);
        driver.switchTo().defaultContent();
        driver.findElement(publishButton).click();
        Wait.isElementPresented(driver.findElement(publishSnackBar));
        dashboardClick();
    }

    @Override
    public void saveEntityAsDraft(String postTitle, String postBody) {
        driver.switchTo().frame(iframe);
        driver.findElement(addTitleField).sendKeys(postTitle);
        driver.findElement(addPostBodyField).sendKeys(postBody);
        driver.switchTo().defaultContent();
        driver.findElement(saveDraftButton).click();
        Wait.isElementPresented(driver.findElement(publishSnackBar));
        Wait.isElementPresented(driver.findElement(By.xpath("//button[contains(text(), 'Saved')]")));
        dashboardClick();
    }

    public void updateEntity(String newPostTitle, String newPostBody){
        driver.switchTo().frame(iframe);
        driver.findElement(addTitleField).clear();
        driver.findElement(existingBodyField).clear();
        driver.findElement(addTitleField).sendKeys(newPostTitle);
        driver.findElement(existingBodyField).sendKeys(newPostBody);
        driver.switchTo().defaultContent();
        driver.findElement(publishButton).click();
        Wait.isElementPresented(driver.findElement(publishSnackBar));
        dashboardClick();
    }
}
