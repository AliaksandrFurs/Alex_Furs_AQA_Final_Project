package elements.tables;

import elements.interfaces.Table;
import elements.rows.PostPageTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PagesPageTable extends elements.Table implements Table {

    WebDriver driver;
    private List<PostPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allRowsTitle;
    private List<WebElement> allAuthorTitle;

    public PagesPageTable(WebDriver driver) {
        this.driver = driver;
        rowTitle = By.xpath("//a[contains(@class, 'row-title')]");
    }

    @Override
    public void createTableRows() {

        updateTableRows();

        allRowsTitle = driver.findElements(rowTitle);
        allAuthorTitle = driver.findElements(authorTitle);

        for(int i=0; i<rowsNumber; i++){

            tableRows.add(new PostPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText()));
        }

        allAuthorTitle.clear();
        allRowsTitle.clear();
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
