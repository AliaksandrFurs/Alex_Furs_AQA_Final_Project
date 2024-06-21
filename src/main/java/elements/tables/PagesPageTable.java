package elements.tables;

import elements.rows.PagesPageTableRow;
import interfaces.tables.ITableWithDraft;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class PagesPageTable extends elements.Table implements ITableWithDraft {

    WebDriver driver;
    private static final String DRAFT_PATTERN = "//tr[@id='%s']//strong/span[contains(text(), 'Draft')]";
    private static final String TITLE_PATTERN = "//a[contains(text(), '%s')]";
    private List<PagesPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();

    public PagesPageTable(WebDriver driver) {
        super();
        this.driver = driver;
        tableLocatorsMap.put("rowTitle", By.xpath("//a[contains(@class, 'row-title')]"));
        tableLocatorsMap.put("rowId", By.xpath("//tr[contains(@id, 'post-')]"));
        tableLocatorsMap.put("rowsNumber", By.xpath("//tbody[@id='the-list']/tr[contains(@id, 'post')]"));
    }

    @Override
    public void createTableRows() {
        if (rowsNumber != 0) {
            getAllRowsTitle().clear();
            allAuthorTitle.clear();
            getAllId().clear();
            tableRows.clear();

            setAllRowsTitle(driver.findElements(tableLocatorsMap.get("rowTitle")));
            allAuthorTitle = driver.findElements(tableLocatorsMap.get("authorTitle"));
            setAllId(driver.findElements(tableLocatorsMap.get("rowId")));

            for (int i = 0; i < rowsNumber; i++) {
                tableRows.add(new PagesPageTableRow(getAllRowsTitle().get(i).getText(), allAuthorTitle.get(i).getText(), getAllId().get(i).getAttribute("id")));
            }
        }else{
            Logging.logWarn("Rows number is 0! Unable to create rows!");
        }

        for(PagesPageTableRow row: tableRows){
            try{
                driver.findElement(By.xpath(String.format(DRAFT_PATTERN, row.getId())));
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
        rowsNumber = driver.findElements(tableLocatorsMap.get("rowsNumber")).size();
    }

    @Override
    public void selectRows() {
        driver.findElement(tableLocatorsMap.get("rowCheckbox")).click();
    }

    @Override
    public boolean isTitleDraft(String title){
        for(PagesPageTableRow row: tableRows){
            if(row.getName().equals(title)){
                return row.isDraft();
            }
        }
        return false;
    }

    @Override
    public void clickOnRowTitle(String rowTitle){
        for(PagesPageTableRow row: tableRows){
            if(row.getName().equals(rowTitle)){
                driver.findElement(By.xpath(String.format(TITLE_PATTERN, rowTitle))).click();
                break;
            }
        }
    }

    @Override
    public List<WebElement> getAllRowsTitle() {
        return allRowsTitle;
    }

    @Override
    public int getRowsNumber() {
        return rowsNumber;
    }

}
