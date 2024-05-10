package elements.tables;

import elements.Table;
import elements.rows.PostPageTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PostsPageTable extends Table implements elements.interfaces.Table {

    WebDriver driver;
    private List<PostPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allRowsTitle = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();


    public PostsPageTable(WebDriver driver) {
        this.driver = driver;

        rowTitle = By.xpath("//a[contains(@class, 'row-title')]");

    }

    @Override
    public void createTableRows() {

        updateRowsNumber();

        allRowsTitle.clear();
        allAuthorTitle.clear();

        allRowsTitle = driver.findElements(rowTitle);
        allAuthorTitle = driver.findElements(authorTitle);

        for(int i=0; i<rowsNumber; i++){

            tableRows.add(new PostPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText()));
        }

    }

    @Override
    public void updateRowsNumber() {

        rowsNumber = driver.findElements(rowTitle).size();

    }

    public List<WebElement> getAllRowsTitle() {
        return allRowsTitle;
    }

    public void deleteTableRows(){

        tableRows.clear();
    }
}
