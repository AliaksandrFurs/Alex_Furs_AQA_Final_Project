package utils;

import enums.MainMenuBarSectionEnum;
import interfaces.IMainMenuBarActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.HashMap;

public class PageActions {


    public static BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName, IMainMenuBarActions mainMenuBar ) {
        return mainMenuBar.ClickOnBarSection(sectionName);
    }

    public static void searchEntity(String entityName, HashMap<String, By> pageLocators, WebDriver driver) {
        driver.findElement(pageLocators.get("searchInput")).sendKeys(entityName);
        driver.findElement(pageLocators.get("searchButton")).click();
    }

    public static void deleteEntity(Select dropdown, String action, HashMap<String, By> pageLocatorsMap, WebDriver driver){
        driver.findElement(pageLocatorsMap.get("rowCheckbox")).click();
        dropdown.selectByValue(action);
        driver.findElement(pageLocatorsMap.get("applyActionButton")).click();
        driver.switchTo().alert().accept();
    }

}
