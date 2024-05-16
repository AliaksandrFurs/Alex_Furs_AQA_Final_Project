package pages;

import elements.interfaces.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

public class EditingPostPage extends BasePage implements Page {

    private final static String EDITINGPOSTS_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/post-new.php";

    private By addTitleField = By.xpath("//h1[contains(@class, 'wp-block wp-block-post-title block-editor-block-list__block editor-post-title editor-post-title__input rich-text')]");

    private By addPostBodyField = By.xpath("//p[contains(@class, 'block-editor-default-block-appender__content')]");

    private By publishButton = By.xpath("//button[contains(text(),'Publish')]");

    private By publishSnackBar = By.xpath("//div[contains(@class, 'components-snackbar-list components-editor-notices__snackbar')]");

    private By dashboardLogo = By.xpath("//a[contains(@class, 'components-button edit-post-fullscreen-mode-close')]");

    @FindBy (name = "editor-canvas")
    private WebElement iframe;


    public EditingPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @Override
    public void openPage() {

        driver.get(EDITINGPOSTS_URL);
        driver.switchTo().frame(iframe);
        Wait.isElementPresented(driver.findElement(addTitleField));

    }

    @Override
    public boolean isOpened() {
        driver.switchTo().frame(iframe);
        Wait.isElementPresented(driver.findElement(addTitleField));
        return true;
    }

    public void dashboardClick(){

        driver.findElement(dashboardLogo).click();

    }

    public void addNewPost(String postTitle, String postBody){

        //driver.switchTo().frame(iframe);
        driver.findElement(addTitleField).sendKeys(postTitle);
        driver.findElement(addPostBodyField).sendKeys(postBody);
        driver.switchTo().defaultContent();
        driver.findElement(publishButton).click();
        Wait.isElementPresented(driver.findElement(publishSnackBar));
    }
}
