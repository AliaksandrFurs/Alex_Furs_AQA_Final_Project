package elements.bars;

import interfaces.IMainMenuBarActions;
import enums.MainMenuBarSectionEnum;
import factories.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.Logging;

public class MainMenuBar implements IMainMenuBarActions {

    WebDriver driver;
    boolean isExpanded;
    private final static String PATTERN = "//div[@id='adminmenumain']//div[contains(text(), '%s')]";

    public By collapseButton = By.id("collapse-button");

    public MainMenuBar(WebDriver driver){

        this.driver = driver;
    }

    @Override
    public BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        setExpandedStatus();
        try {
            By xpath = By.xpath(String.format(PATTERN, sectionName.getValue()));
            if(isExpanded == false){
                driver.findElement(collapseButton).click();
            }
            switch(sectionName){
                case POSTS : {
                    driver.findElement(xpath).click();
                    return PageFactory.getPostsPage(driver);
                }
                case MEDIA: {
                    driver.findElement(xpath).click();
                    return PageFactory.getMediaPage(driver);
                }
                case PAGES: {
                    driver.findElement(xpath).click();
                    return PageFactory.getPagesPage(driver);
                }
            }
        }catch(NoSuchElementException e ){
            Logging.logError("No " + sectionName.getValue() + " element on the page");
        }
    return null;
    }

    @Override
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {
        return driver.findElements(By.xpath(String.format(PATTERN, sectionName.getValue()))).size() > 0;
    }

    @Override
    public void setExpandedStatus() {
        if(driver.findElement(collapseButton).getAttribute("aria-expanded").equals("true")){
            isExpanded = true;
        }else{
            isExpanded = false;
        }
    }
}
