package pages;

import elements.interfaces.Page;
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
    public void openPage() {
        driver.get(URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
    }

    @Override
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageNameLocator));
        return true;
    }

    public void uploadNewImage(){
        uploadButton.sendKeys("C:\\Users\\37529\\IdeaProjects\\Alex_Furs_AQA_Final_Project\\src\\test\\resources\\Screenshot_1.jpg");
        submitButton.click();
        Wait.isElementPresented(driver.findElement(pageNameLocator));
    }

    public void uploadEmptyImage(){
        submitButton.click();
        Wait.isElementPresented(driver.findElement(errorUpload));
    }

    public boolean isErrorPageOpens(){
        Wait.isElementPresented(driver.findElement(errorUpload));
        if(driver.findElement(errorUpload).getText().equals("No file was uploaded.")){
            return true;
        }else{
            return false;
        }
    }


}
