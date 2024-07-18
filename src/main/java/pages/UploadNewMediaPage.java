package pages;

import interfaces.pages.IUploadNewMediaPageInterface;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;
import static enums.LocatorsEnum.*;

public class UploadNewMediaPage extends BasePage implements IUploadNewMediaPageInterface {

    private final static String URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/media-new.php";

    public UploadNewMediaPage(WebDriver driver) {
        super(driver);
        setPageName("Upload New Media");
        pageLocatorsMap.put(SUBMITUPLOADBUTTONLOCATOR, By.id("html-upload"));
        pageLocatorsMap.put(UPLOADBUTTONLOCATOR, By.id("async-upload"));
        pageLocatorsMap.put(ERRORUPLOADLOCATOR, By.className("wp-die-message"));
        PageFactory.initElements(driver,this);
    }

    @Override
    @Step("Open media library page")
    public void openPage() {
        driver.get(URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
    }

    @Override
    @Step("Verify is media library page is opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        return true;
    }

    @Step("Upload one media")
    public void uploadNewImage(String fileName){
        if(fileName.equals("Screenshot_1")){
            driver.findElement(pageLocatorsMap.get(UPLOADBUTTONLOCATOR)).sendKeys("C:\\Users\\37529\\IdeaProjects\\Alex_Furs_AQA_Final_Project\\src\\test\\resources\\Screenshot_1.jpg");
            driver.findElement(pageLocatorsMap.get(SUBMITUPLOADBUTTONLOCATOR)).click();
            Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(PAGENAMELOCATOR)));
        }else{
            driver.findElement(pageLocatorsMap.get(SUBMITUPLOADBUTTONLOCATOR)).click();
            Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(ERRORUPLOADLOCATOR)));
            driver.navigate().back();
        }
    }
}
