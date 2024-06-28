package elements.tables;

import elements.rows.MediaPageTableRow;
import elements.Table;
import interfaces.tables.ITable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class MediaPageTable extends Table implements ITable {

    WebDriver driver;
    //private final static String TITLE_PATTERN = "//span[contains(text(),'%s]";
    private final static String COLTITLEFINDPATTERN = "//tbody[@id='the-list']/tr[@id='%s']//strong/a";
    private final static String COLAUTHORFINDPATTERN = "//tbody[@id='the-list']/tr[@id='%s']//td[@class='author column-author']/a";
    private List<MediaPageTableRow> tableRows = new ArrayList<>();

    public MediaPageTable(WebDriver driver) {
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
                    tableRows.add(new MediaPageTableRow(element.getAttribute("id")));
                }
            }else if(allId.size() == 0){
                Logging.logWarn("No rows available");
            }
        for(MediaPageTableRow row : tableRows){
            String id = row.getId();
            String xpathTitle = String.format(COLTITLEFINDPATTERN, id);
            String xpathAuthor = String.format(COLAUTHORFINDPATTERN, id);
            row.setTitle(driver.findElement(By.xpath(xpathTitle)).getText());
            row.setAuthor(driver.findElement(By.xpath(xpathAuthor)).getText());
        }
    }

    @Override
    public void selectRows() {
        driver.findElement(tableLocatorsMap.get("rowCheckbox")).click();
    }

}
