package pages;

import enums.AddingEntityTypeEnum;
import enums.LocatorsEnum;
import interfaces.pages.ICreatePageInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Wait;
import java.util.HashMap;
import static enums.LocatorsEnum.*;

public class CreatePage implements ICreatePageInterface {


    WebDriver driver;
    private final static String PAGE_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/post-new.php?post_type=page";
    private final static String POST_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/post-new.php";
    private HashMap<LocatorsEnum, By> pageLocatorsMap;

    public CreatePage(WebDriver driver){
        this.driver = driver;
        pageLocatorsMap = new HashMap<>();
        pageLocatorsMap.put(ADDTITLEFIELDLOCATOR,By.xpath("//h1[contains(@class, 'wp-block wp-block-post-title block-editor-block-list__block editor-post-title editor-post-title__input rich-text')]"));
        pageLocatorsMap.put(ADDPOSTBODYFIELSLOCATOR, By.xpath("//p[contains(@class, 'block-editor-default-block-appender__content')]"));
        pageLocatorsMap.put(EXISTINGBODYFIELDLOCATOR, By.xpath("//p[contains(@class, 'block-editor-rich-text__editable block-editor-block-list__block wp-block wp-block-paragraph rich-text')]"));
        pageLocatorsMap.put(PUBLISHBUTTONLOCATOR, By.xpath("//button[contains(@class, 'components-button editor-post-publish-button editor-post-publish-button__button is-primary')]"));
        pageLocatorsMap.put(PUBLISHSNACKBARLOCATOR, By.xpath("//div[contains(@class, 'components-snackbar-list components-editor-notices__snackbar')]"));
        pageLocatorsMap.put(DASHBOARDLOGOLOCATOR, By.xpath("//a[contains(@class, 'components-button edit-post-fullscreen-mode-close')]"));
        pageLocatorsMap.put(SAVEDRAFTBUTTONLOCATOR, By.xpath("//button[contains(@class, 'components-button editor-post-save-draft is-tertiary')]"));
        pageLocatorsMap.put(IFRAMELOCATOR, By.xpath("//div//iframe[@name='editor-canvas']"));
    }
    @Override
    public void openPage(AddingEntityTypeEnum entityType) {
        switch(entityType){
            case PAGE -> driver.get(PAGE_URL);
            case POST -> driver.get(POST_URL);
        }
        driver.switchTo().frame(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(ADDTITLEFIELDLOCATOR)));
    }

    @Override
    public boolean isOpened() {
        driver.switchTo().frame(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(ADDTITLEFIELDLOCATOR)));
        return true;
    }

    @Override
    public void dashboardClick() {
        driver.findElement(pageLocatorsMap.get(DASHBOARDLOGOLOCATOR)).click();
    }

    @Override
    public void addNewEntity(String postTitle, String postBody) {
        Wait.isFrameDisplayed(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        driver.findElement(pageLocatorsMap.get(ADDTITLEFIELDLOCATOR)).sendKeys(postTitle);
        driver.findElement(pageLocatorsMap.get(ADDPOSTBODYFIELSLOCATOR)).sendKeys(postBody);
        driver.switchTo().defaultContent();
        driver.findElement(pageLocatorsMap.get(PUBLISHBUTTONLOCATOR)).click();
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PUBLISHSNACKBARLOCATOR)));
        dashboardClick();
    }

    @Override
    public void saveEntityAsDraft(String postTitle, String postBody) {
        Wait.isFrameDisplayed(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        driver.findElement(pageLocatorsMap.get(ADDTITLEFIELDLOCATOR)).sendKeys(postTitle);
        driver.findElement(pageLocatorsMap.get(ADDPOSTBODYFIELSLOCATOR)).sendKeys(postBody);
        driver.switchTo().defaultContent();
        driver.findElement(pageLocatorsMap.get(SAVEDRAFTBUTTONLOCATOR)).click();
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PUBLISHSNACKBARLOCATOR)));
        Wait.isElementPresented(driver.findElement(By.xpath("//button[contains(text(), 'Saved')]")));
        dashboardClick();
    }

    public void updateEntity(String newPostTitle, String newPostBody){
        Wait.isFrameDisplayed(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(pageLocatorsMap.get(IFRAMELOCATOR)));
        driver.findElement(pageLocatorsMap.get(ADDTITLEFIELDLOCATOR)).clear();
        driver.findElement(pageLocatorsMap.get(EXISTINGBODYFIELDLOCATOR)).clear();
        driver.findElement(pageLocatorsMap.get(ADDTITLEFIELDLOCATOR)).sendKeys(newPostTitle);
        driver.findElement(pageLocatorsMap.get(EXISTINGBODYFIELDLOCATOR)).sendKeys(newPostBody);
        driver.switchTo().defaultContent();
        driver.findElement(pageLocatorsMap.get(PUBLISHBUTTONLOCATOR)).click();
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PUBLISHSNACKBARLOCATOR)));
        dashboardClick();
    }
}
