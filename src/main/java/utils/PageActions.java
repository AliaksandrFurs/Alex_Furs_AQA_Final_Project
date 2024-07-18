package utils;

import enums.DeleteActionsEnum;
import enums.LocatorsEnum;
import enums.MainMenuBarSectionEnum;
import interfaces.IMainMenuBarActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import static enums.LocatorsEnum.*;
import java.util.HashMap;

public class PageActions {


    public static BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName, IMainMenuBarActions mainMenuBar ) {
        return mainMenuBar.ClickOnBarSection(sectionName);
    }

    public static void searchEntity(String entityName, HashMap<LocatorsEnum, By> pageLocators, WebDriver driver) {
        driver.findElement(pageLocators.get(SEARCHINPUTLOCATOR)).sendKeys(entityName);
        driver.findElement(pageLocators.get(SEARCHBUTTONLOCATOR)).click();
    }

    public static void deleteEntity(Select dropdown, DeleteActionsEnum action, HashMap<LocatorsEnum, By> pageLocatorsMap, WebDriver driver){
        driver.findElement(pageLocatorsMap.get(ROWCHECKBOXLOCATOR)).click();
        dropdown.selectByValue(action.toString());
        driver.findElement(pageLocatorsMap.get(APPLYACTIONBUTTONLOCATOR)).click();
        driver.switchTo().alert().accept();
    }

}
