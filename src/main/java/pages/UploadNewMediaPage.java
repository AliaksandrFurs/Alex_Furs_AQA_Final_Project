package pages;

import interfaces.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

public class UploadNewMediaPage extends BasePage implements Page {

    private final static String URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/media-new.php";

    private By errorUpload = By.className("wp-die-message");

    @FindBy(id = "html-upload")
    WebElement submitButton;

    @FindBy(id = "async-upload")
    WebElement uploadButton;

    public UploadNewMediaPage(WebDriver driver) {
        super(driver);
        setPageName("Upload New Media");
        PageFactory.initElements(driver,this);
    }

    @Override
    @Step("Open meadi library page")
    public void openPage() {
        driver.get(URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
    }

    @Override
    @Step("Verify is media library page is opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    @Step("Upload one media")
    public void uploadNewImage(String fileName){
        if(fileName.equals("Screenshot_1")){
            uploadButton.sendKeys("C:\\Users\\37529\\IdeaProjects\\Alex_Furs_AQA_Final_Project\\src\\test\\resources\\Screenshot_1.jpg");
            submitButton.click();
            Wait.isElementPresented(driver.findElement(pageNameLocator));
        }else{
            submitButton.click();
            Wait.isElementPresented(driver.findElement(errorUpload));
            driver.navigate().back();
        }
    }
}
