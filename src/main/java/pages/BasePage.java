package pages;

import elements.bars.MainMenuBar;
import interfaces.IMainMenuBarActions;
import interfaces.TopPageBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public abstract class BasePage  {

    WebDriver driver;
    public HashMap <String, By> pageLocatorsMap;
    String pageName;
    IMainMenuBarActions mainMenuBar;
    TopPageBar topPageBar;

    public BasePage(WebDriver driver){

        this.driver = driver;
        pageLocatorsMap = new HashMap<>();
        pageLocatorsMap.put("pageNameLocator", By.xpath("//div[@class='wrap']//h1"));
        pageLocatorsMap.put("noEntityFoundLocator", By.className("colspanchange"));
        mainMenuBar = new MainMenuBar(driver);
        topPageBar = new elements.bars.TopPageBar(driver);
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

}
