package elements.tables;

import elements.interfaces.Table;
import elements.rows.PagesPageTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class PagesPageTable extends elements.Table implements Table {

    WebDriver driver;
    private static final String PATTERN = "//tr[@id='%s']//strong/span[contains(text(), 'Draft')]";
    private List<PagesPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allRowsTitle = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();
    private List<WebElement> allId = new ArrayList<>();

    public PagesPageTable(WebDriver driver) {
        this.driver = driver;
        rowTitle = By.xpath("//a[contains(@class, 'row-title')]");
    }

    @Override
    public void createTableRows() {
        updateRowsNumber();

        allRowsTitle.clear();
        allAuthorTitle.clear();
        allId.clear();
        tableRows.clear();

        allRowsTitle = driver.findElements(rowTitle);
        allAuthorTitle = driver.findElements(authorTitle);
        allId = driver.findElements(rowId);

        for(int i=0; i<rowsNumber; i++){
            tableRows.add(new PagesPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText(), allId.get(i).getAttribute("id")));
        }

        for(PagesPageTableRow row: tableRows){
            try{
                driver.findElement(By.xpath(String.format(PATTERN, row.getId())));
                row.setDraft(true);
            }catch(NoSuchElementException e){
                row.setDraft(false);
                Logging.logWarn("Element not exists");
            }
        }
    }

    @Override
    public void deleteTableRows() {
        tableRows.clear();
    }

    @Override
    public void updateRowsNumber() {
        rowsNumber = driver.findElements(rowTitle).size();
    }

    @Override
    public void selectRows() {
        driver.findElement(rowCheckbox).click();
    }

    public boolean isTitleDraft(String title){
        for(PagesPageTableRow row: tableRows){
            if(row.getName().equals(title)){
                return row.isDraft();
            }
        }
        return false;
    }

    public List<WebElement> getAllRowsTitle() {
        return allRowsTitle;
    }
}
