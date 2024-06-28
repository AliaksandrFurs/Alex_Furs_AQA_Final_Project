package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class Table {

    public HashMap<String, By> tableLocatorsMap;

    public Table(){
        tableLocatorsMap = new HashMap<>();
        tableLocatorsMap.put("rowCheckbox", By.xpath("//input[contains(@id, 'cb-select')]"));
        tableLocatorsMap.put("rowsNumber", By.xpath("//tbody[@id='the-list']//tr[contains(@id, 'post')]"));
    }

}
