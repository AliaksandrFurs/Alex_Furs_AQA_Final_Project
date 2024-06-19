package elements.tables;

import elements.rows.MediaPageTableRow;
import elements.Table;
import interfaces.tables.IMediaPageTableInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MediaPageTable extends Table implements IMediaPageTableInterface {



    WebDriver driver;
    private final static String TITLE_PATTERN = "//a[text()[contains(.,'%s)]]";
    private List<MediaPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();

    public MediaPageTable(WebDriver driver) {
        super();
        this.driver = driver;
        tableLocatorsMap.put("rowTitle", By.xpath("//strong[@class='has-media-icon']/a"));
        //rowTitle = By.xpath("//strong[@class='has-media-icon']/a");
    }

    @Override
    public void createTableRows() {
        updateRowsNumber();

        getAllRowsTitle().clear();
        allAuthorTitle.clear();
        getAllId().clear();
        tableRows.clear();

        setAllRowsTitle(driver.findElements(rowTitle));
        allAuthorTitle = driver.findElements(tableLocatorsMap.get("authorTitle"));
        setAllId(driver.findElements(tableLocatorsMap.get("rowId")));

        for(int i=0; i<rowsNumber; i++){
            tableRows.add(new MediaPageTableRow(getAllRowsTitle().get(i).getText(), allAuthorTitle.get(i).getText(), getAllId().get(i).getAttribute("id")));
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
        driver.findElement(tableLocatorsMap.get("rowCheckbox")).click();
    }

    @Override
    public void clickOnRowTitle(String rowTitle) {
        for(MediaPageTableRow row: tableRows){
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
    public MediaPageTableRow getRowByTitle(String rowTitle) {
            for(MediaPageTableRow row : tableRows){
                if(row.getName().equals(rowTitle)){
                    return row;
                }
            }
            return null;
    }
}
