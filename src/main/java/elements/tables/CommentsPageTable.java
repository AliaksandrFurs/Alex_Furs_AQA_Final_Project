package elements.tables;

import elements.Table;
import elements.rows.PostPageTableRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CommentsPageTable extends Table implements elements.interfaces.Table {

    WebDriver driver;
    public List<PostPageTableRow> tableRows = new ArrayList<>();
    List<WebElement> allRowsTitle = driver.findElements(rowTitle);
    List<WebElement> allAuthorTitle = driver.findElements(authorTitle);

    public CommentsPageTable(WebDriver driver) {
        super(driver);
    }

    @Override
    public void createTableRows() {

        for(int i=0; i<rowsNumber; i++){

            tableRows.add(new PostPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText()));
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