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
    public List<MediaPageTableRow> tableRows = new ArrayList<>();
    List<WebElement> allRowsTitle = driver.findElements(rowTitle);
    List<WebElement> allAuthorTitle = driver.findElements(authorTitle);

    public MediaPageTable(WebDriver driver) {

        super(driver);
        rowTitle = By.xpath("//strong[@class='has-media-icon']/a");

        updateRowsNumber();

        createTableRows();

    }

    @Override
    public void createTableRows() {

        for(int i=0; i<rowsNumber; i++){

            tableRows.add(new MediaPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText()));

        }
    }

    @Override
    public void updateTableRows() {

        if(tableRows.size() != 0){

            updateRowsNumber();
            createTableRows();
        }
    }

    @Override
    public void updateRowsNumber() {

        rowsNumber = driver.findElements(rowTitle).size();

    }
}
