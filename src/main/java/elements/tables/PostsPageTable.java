package elements.tables;

import elements.Table;
import elements.rows.PostPageTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PostsPageTable extends Table {

    public List<PostPageTableRow> tableRows = new ArrayList<>();


    public PostsPageTable(WebDriver driver) {
        super(driver);

        rowTitle = By.xpath("//a[contains(@class, 'row-title')]");

        rowsNumber = driver.findElements(rowTitle).size();

        List<WebElement> allRowsTitle = driver.findElements(rowTitle);
        List<WebElement> allAuthorTitle = driver.findElements(authorTitle);

        for(int i=0; i<rowsNumber; i++){

            tableRows.add(new PostPageTableRow(allRowsTitle.get(i).getText(), allAuthorTitle.get(i).getText()));
        }




    }
}
