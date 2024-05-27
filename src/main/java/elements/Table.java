package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class Table {

    //WebDriver driver;
    public int rowsNumber;
    private int columnsNUmber;
    private List<WebElement> allRowsTitle = new ArrayList<>();
    private List<WebElement> allId = new ArrayList<>();

    public By rowTitle;
    public By authorTitle = By.xpath("//tbody[@id='the-list']//td[@class='author column-author']/a");
    public By rowCheckbox = By.xpath("//input[contains(@id, 'cb-select')]");
    public By rowId = By.xpath("//tr[contains(@id, 'post-')]");

    public List<WebElement> getAllRowsTitle() {
        return allRowsTitle;
    }

    public void setAllRowsTitle(List<WebElement> allRowsTitle) {
        this.allRowsTitle = allRowsTitle;
    }

    public List<WebElement> getAllId() {
        return allId;
    }

    public void setAllId(List<WebElement> allId) {
        this.allId = allId;
    }
}
