package pages;

import elements.bars.MainMenuBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public abstract class BasePage {

    WebDriver driver;
    public By pageNameLocator = By.xpath("//div[@class='wrap']//h1");
    public String pageName;
    MainMenuBar mainMenuBar = new MainMenuBar();

    public BasePage(WebDriver driver){

        this.driver = driver;
    }
}
