package elements.tables;

import elements.rows.PagesPageTableRow;
import interfaces.tables.ITable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class PagesPageTable extends elements.Table implements ITable {

    WebDriver driver;
    private List<PagesPageTableRow> tableRows = new ArrayList<>();

    public PagesPageTable(WebDriver driver) {
        super();
        this.driver = driver;
        tableLocatorsMap.put("rowId", By.xpath("//tr[contains(@id, 'post-')]"));
    }

    @Override
    public void createTableRows() {
        tableRows.clear();
        List <WebElement> allId = driver.findElements(tableLocatorsMap.get("rowId"));
        if(allId.size() > 0){
            for (WebElement element : allId) {
                tableRows.add(new PagesPageTableRow(element.getAttribute("id")));
            }
        }else if(allId.size() == 0){
            Logging.logWarn("No rows available");
        }
        //TBD
    }

    @Override
    public void selectRows() {
        driver.findElement(tableLocatorsMap.get("rowCheckbox")).click();
    }

}
