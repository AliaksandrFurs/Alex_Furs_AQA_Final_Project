package elements.tables;

import interfaces.Table;
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
    private static final String DRAFT_PATTERN = "//tr[@id='%s']//strong/span[contains(text(), 'Draft')]";
    private static final String TITLE_PATTERN = "//a[contains(text(), '%s')]";
    private List<PagesPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();

    public PagesPageTable(WebDriver driver) {
        this.driver = driver;
        rowTitle = By.xpath("//a[contains(@class, 'row-title')]");
    }

    @Override
    public void createTableRows() {
        updateRowsNumber();

        getAllRowsTitle().clear();
        allAuthorTitle.clear();
        getAllId().clear();
        tableRows.clear();

        setAllRowsTitle(driver.findElements(rowTitle));
        allAuthorTitle = driver.findElements(authorTitle);
        setAllId(driver.findElements(rowId));

        for(int i=0; i<rowsNumber; i++){
            tableRows.add(new PagesPageTableRow(getAllRowsTitle().get(i).getText(), allAuthorTitle.get(i).getText(), getAllId().get(i).getAttribute("id")));
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

    @Override
    public void clickOnRowTitle(String rowTitle){
        for(PagesPageTableRow row: tableRows){
            if(row.getName().equals(rowTitle)){
                driver.findElement(By.xpath(String.format(TITLE_PATTERN, rowTitle))).click();
                break;
            }
        }
    }

    public PagesPageTableRow getRowByTitle(String rowTitle){
        for(PagesPageTableRow row : tableRows){
            if(row.getName().equals(rowTitle)){
                return row;
            }
        }
        return null;
    }
}
