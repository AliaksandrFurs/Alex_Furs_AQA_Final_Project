package pages;

import elements.bars.MainMenuBar;
import interfaces.TopPageBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    WebDriver driver;
    public By pageNameLocator = By.xpath("//div[@class='wrap']//h1");
    public By noEntityFoundLocator = By.className("colspanchange");
    private String pageName;

    MainMenuBar mainMenuBar;
    TopPageBar topPageBar;

    public BasePage(WebDriver driver){

        this.driver = driver;
        mainMenuBar = new MainMenuBar(driver);
        topPageBar = new elements.bars.TopPageBar(driver);
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
