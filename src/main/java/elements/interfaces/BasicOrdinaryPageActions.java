package elements.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public interface BasicOrdinaryPageActions {

    By searchInput = By.id("post-search-input");
    By searchButton = By.id("search-submit");
    By applyActionButton = By.id("doaction");
    By addNewEntityButton = By.xpath("//a[contains(@class, 'page-title-action')]");

    void searchEntity(String entityName);

    boolean isEntityAvailable();

    void deleteEntity(String enityName);

    void openAddingEntityPage();
}
