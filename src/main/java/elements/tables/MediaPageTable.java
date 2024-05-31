package elements.tables;

import elements.rows.MediaPageTableRow;
import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MediaPageTable extends Table implements interfaces.Table {



    WebDriver driver;
    private final static String TITLE_PATTERN = "//a[text()[contains(.,'%s)]]";
    private List<MediaPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();

    public MediaPageTable(WebDriver driver) {
        this.driver = driver;
        rowTitle = By.xpath("//strong[@class='has-media-icon']/a");
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
        driver.findElement(rowCheckbox).click();
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

    public MediaPageTableRow getRowByTitle(String rowTitle){
        for(MediaPageTableRow row : tableRows){
            if(row.getName().equals(rowTitle)){
                return row;
            }
        }
        return null;
    }
}
