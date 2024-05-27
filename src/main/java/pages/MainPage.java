package pages;

import elements.interfaces.MainMenuBarActions;
import elements.interfaces.Page;
import enums.MainMenuBarSectionEnum;
import io.qameta.allure.Step;
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
    @Step("Open dashboard page")
    public void openPage() {
        driver.get(DASHBOARD_URL);
        Wait.isElementPresented(driver.findElement(pageNameLocator));
    }

    @Override
    @Step("Verify is dashboard page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(siteHealthStatus);
        return true;
    }

    @Override
    @Step("Click on main menu bar section")
    public BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        return mainMenuBar.ClickOnBarSection(sectionName);
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }
}
