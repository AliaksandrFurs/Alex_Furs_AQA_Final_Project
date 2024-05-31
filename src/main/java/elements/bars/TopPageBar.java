package elements.bars;

import enums.TopPageBarEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopPageBar implements interfaces.TopPageBar {

    WebDriver driver;

    public TopPageBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "wp-admin-bar-site-name")
    WebElement wordPress;

    @FindBy(id = "wp-admin-bar-updates")
    WebElement updates;

    @FindBy(id = "wp-admin-bar-comments")
    WebElement comments;

    @FindBy(id = "wp-admin-bar-new-content")
    WebElement content;

    @FindBy(id = "wp-admin-bar-w3tc")
    WebElement performance;

    @FindBy(id = "wp-admin-bar-top-secondary")
    WebElement admin;

    @Override
    public void ClickOnBarSection(TopPageBarEnum sectionName) {
        switch(sectionName){
            case WORDPRESS -> wordPress.click();
            case ADMIN -> admin.click();
            case LIKES -> comments.click();
            case PERFORMANCE -> performance.click();
            case NEW -> content.click();
            case RELOAD -> updates.click();
        }
    }

    @Override
    public boolean isSectionPresented(TopPageBarEnum sectionName) {
        switch(sectionName){
            case WORDPRESS -> {
                return wordPress.isDisplayed();
            }
            case ADMIN -> {
                return admin.isDisplayed();
            }
            case LIKES -> {
                return comments.isDisplayed();
            }
            case PERFORMANCE -> {
                return performance.isDisplayed();
            }
            case NEW -> {
                return content.isDisplayed();
            }
            case RELOAD -> {
                return updates.isDisplayed();
            }
        }
        return  false;
    }
}
