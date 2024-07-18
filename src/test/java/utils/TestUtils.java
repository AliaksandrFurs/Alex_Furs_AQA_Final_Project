package utils;

import interfaces.pages.IPage;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static enums.LocatorsEnum.*;

public class TestUtils {

    static WebDriver driver = Browser.getDriver();

    @Step("Verify is entity presented on page")
    public static boolean isEntityAvailable(IPage page, String entityName) {
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(TABLELOCATOR)));
        List <WebElement> allId = driver.findElements(page.getPageLocatorsMap().get(ROWIDLOCATOR));
        if(allId.size() == 0){
            Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(NOENTITYFOUNDLOCATOR)));
            if(driver.findElement(page.getPageLocatorsMap().get(NOENTITYFOUNDLOCATOR)).getText().equals("No media files found.")){
                return false;
            }
        }else{
            if(allId.size() > 0){
                List<WebElement> allTitles = driver.findElements(page.getPageLocatorsMap().get(TITLELOCATOR));
                for(WebElement element : allTitles){
                    if(element.getText().equals(entityName)){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Step("Verisy is post is a draft")
    public static boolean isEntityDraft(IPage page, String postTitle){
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(TABLELOCATOR)));
        List<WebElement> allTitles = driver.findElements(page.getPageLocatorsMap().get(TITLELOCATOR));
        for(WebElement element : allTitles){
            if(element.getText().equals(postTitle)){
                try{
                    driver.findElement(page.getPageLocatorsMap().get(DRAFTLOCATOR));
                    return true;
                }catch(NoSuchElementException e){
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean verifyIsTitleCorrect(IPage page, String postTitle){
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(TABLELOCATOR)));
        List<WebElement> allTitles = driver.findElements(page.getPageLocatorsMap().get(TITLELOCATOR));
        if(allTitles.size() > 0){
            for(WebElement author : allTitles){
                if(author.getText().equals(postTitle)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verifyIsAuthorCorrect(IPage page, String authorName){
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(TABLELOCATOR)));
        List<WebElement> allAuthorNames = driver.findElements(page.getPageLocatorsMap().get(AUTHORNAMELOCATOR));
        if(allAuthorNames.size() > 0){
            for(WebElement author : allAuthorNames){
                if(author.getText().equals(authorName)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verifyIsDateCorrect(IPage page, String date){
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(TABLELOCATOR)));
        List<WebElement> allDates = driver.findElements(page.getPageLocatorsMap().get(DATELOCATOR));
        if(allDates.size() > 0){
            for(WebElement author : allDates){
                if(author.getText().equals(date)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verifyIsCommentsCorrect(IPage page, String commentCounter){
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(PAGENAMELOCATOR)));
        Wait.isElementPresented(driver.findElement(page.getPageLocatorsMap().get(TABLELOCATOR)));
        List<WebElement> allDates = driver.findElements(page.getPageLocatorsMap().get(COMMENTLOCATOR));
        if(allDates.size() > 0){
            for(WebElement author : allDates){
                if(author.getText().equals(commentCounter)){
                    return true;
                }
            }
        }
        return false;
    }
}
