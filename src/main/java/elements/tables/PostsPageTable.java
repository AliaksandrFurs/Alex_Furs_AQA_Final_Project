package elements.tables;

import elements.Table;
import elements.rows.PostPageTableRow;
import interfaces.tables.ITable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class PostsPageTable extends Table implements ITable {

    WebDriver driver;
    private static final String PATTERN = "//tr[@id='%s']//strong/span[contains(text(), 'Draft')]";
    private static final String TITLE_PATTERN = "//a[contains(text(), '%s')]";
    private List<PostPageTableRow> tableRows = new ArrayList<>();


    public PostsPageTable(WebDriver driver) {
        super();
        this.driver = driver;
        tableLocatorsMap.put("rowTitle", By.xpath("//a[contains(@class, 'row-title')]"));
        tableLocatorsMap.put("rowId", By.xpath("//tr[contains(@id, 'post-')]"));
    }

    @Override
    public void createTableRows() {
        tableRows.clear();
        List <WebElement> allId = driver.findElements(tableLocatorsMap.get("rowId"));
        if(allId.size() > 0){
            for (WebElement element : allId) {
                tableRows.add(new PostPageTableRow(element.getAttribute("id")));
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
