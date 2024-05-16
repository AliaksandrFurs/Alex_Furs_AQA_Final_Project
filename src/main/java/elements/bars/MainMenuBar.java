package elements.bars;

import elements.interfaces.MainMenuBarActions;
import enums.MainMenuBarSectionEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utils.Logging;

public class MainMenuBar implements MainMenuBarActions {

    WebDriver driver;
    private final static String PATTERN = "//div[@id = 'wpwrap']//div[contains(text(), '%s)']";


    @Override
    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName) {
        try {

            By xpath = By.xpath(String.format(PATTERN, sectionName.getValue()));
            driver.findElement(xpath).click();

        }catch(NoSuchElementException e ){

            Logging.logError("No " + sectionName.getValue() + " element on the page");
        }

    }

    @Override
    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName) {

        return driver.findElements(By.xpath(String.format(PATTERN, sectionName.getValue()))).size() > 0;
    }
}
