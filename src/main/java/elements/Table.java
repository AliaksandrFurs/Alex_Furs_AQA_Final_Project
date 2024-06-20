package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class Table {

    public int rowsNumber;
    public HashMap<String, By> tableLocatorsMap;
    public List<WebElement> allRowsTitle = new ArrayList<>();
    public List<WebElement> allId = new ArrayList<>();
    //public List<WebElement> allRows = new ArrayList<>();

    public Table(){
        tableLocatorsMap = new HashMap<>();
        tableLocatorsMap.put("authorTitle", By.xpath("//tbody[@id='the-list']//td[@class='author column-author']/a"));
        tableLocatorsMap.put("rowCheckbox", By.xpath("//input[contains(@id, 'cb-select')]"));
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
