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

        allRowsTitle = driver.findElements(rowTitle);
        allAuthorTitle = driver.findElements(authorTitle);

        for(int i=0; i<rowsNumber; i++){

            tableRows.add(new PostPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText(), allId.get(i).getAttribute("id")));
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

    }
}
