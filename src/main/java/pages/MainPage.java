package pages;

import elements.interfaces.MainMenuBarActions;
import elements.interfaces.Page;
import enums.MainMenuBarSectionEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

public class MainPage extends BasePage implements Page, MainMenuBarActions {

    private final static String DASHBOARD_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/index.php";

    @FindBy(id = "dashboard_site_health")
    private WebElement siteHealthStatus;

    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        setPageName("Dashboard");
    }


    @Override
    public void openPage() {

    }

    @Override
    public boolean isOpened() {
        Wait.isElementPresented(siteHealthStatus);
        return true;
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
