package pages;

import elements.bars.MainMenuBar;
import enums.LocatorsEnum;
import interfaces.IMainMenuBarActions;
import interfaces.TopPageBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

import static enums.LocatorsEnum.*;

public abstract class BasePage  {

    WebDriver driver;
    public HashMap <LocatorsEnum, By> pageLocatorsMap;
    String pageName;
    IMainMenuBarActions mainMenuBar;
    TopPageBar topPageBar;

    public BasePage(WebDriver driver){

        this.driver = driver;
        pageLocatorsMap = new HashMap<>();
        pageLocatorsMap.put(PAGENAMELOCATOR, By.xpath("//div[@class='wrap']//h1"));
        pageLocatorsMap.put(NOENTITYFOUNDLOCATOR, By.className("colspanchange"));
        pageLocatorsMap.put(ROWCHECKBOXLOCATOR, By.xpath("//input[contains(@id, 'cb-select')]"));
        mainMenuBar = new MainMenuBar(driver);
        topPageBar = new elements.bars.TopPageBar(driver);
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

}
