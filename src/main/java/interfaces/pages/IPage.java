package interfaces.pages;
import enums.MainMenuBarSectionEnum;
import org.openqa.selenium.By;
import java.util.HashMap;

public interface IPage {

    void openPage();

    boolean isOpened();

    void searchEntity(String entityName);

    boolean isSectionPresented(MainMenuBarSectionEnum sectionName);

    void deleteEntity(String enityName);

    void openAddingEntityPage();

    void clickOnEntity(String entityName);

    void ClickOnBarSection(MainMenuBarSectionEnum sectionName);

    HashMap <String, By> getPageLocatorsMap();

}
