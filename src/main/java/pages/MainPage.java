package pages;

import interfaces.pages.IMainPageInterface;
import enums.MainMenuBarSectionEnum;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PageActions;
import utils.Wait;

public class MainPage extends BasePage implements IMainPageInterface {

    private final static String DASHBOARD_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/index.php";


    public MainPage(WebDriver driver){
        super(driver);
        pageLocatorsMap.put("siteHealthStatus", By.id("dashboard_site_health"));
        setPageName("Dashboard");
    }


    @Override
    @Step("Open dashboard page")
    public void openPage() {
        driver.get(DASHBOARD_URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("pageNameLocator")));
    }

    @Override
    @Step("Verify is dashboard page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("siteHealthStatus")));
        return true;
    }

    @Override
    @Step("Click on main menu bar section")
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        //return mainMenuBar.ClickOnBarSection(sectionName);
        PageActions.ClickOnBarSection(sectionName, mainMenuBar);
    }

    @Override
    @Step("Verify is main menu bar section presented")
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return false;
    }

}
