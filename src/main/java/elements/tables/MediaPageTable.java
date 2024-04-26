package elements.tables;

import elements.rows.MediaPageTableRow;
import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MediaPageTable extends Table implements elements.interfaces.Table {

    WebDriver driver;
    private List<MediaPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allRowsTitle;
    private List<WebElement> allAuthorTitle;

    public MediaPageTable(WebDriver driver) {

        this.driver = driver;
        rowTitle = By.xpath("//strong[@class='has-media-icon']/a");

    }

    @Override
    public void createTableRows() {

        updateTableRows();

        allRowsTitle = driver.findElements(rowTitle);
        allAuthorTitle = driver.findElements(authorTitle);

        for(int i=0; i<rowsNumber; i++){

            tableRows.add(new MediaPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText()));

        }

        allRowsTitle.clear();
        allAuthorTitle.clear();
    }

    @Override
    public void updateTableRows() {

        if(tableRows.size() > 0){

            updateRowsNumber();
            createTableRows();
        }
    }

    @Override
    public void updateRowsNumber() {

        rowsNumber = driver.findElements(rowTitle).size();

    }
}
