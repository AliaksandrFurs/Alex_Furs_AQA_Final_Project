package elements.tables;

import elements.Table;
import elements.rows.PostPageTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class PostsPageTable extends Table implements elements.interfaces.Table {

    WebDriver driver;
    private static final String PATTERN = "//tr[@id='%s']//strong/span[contains(text(), 'Draft')]";
    private static final String TITLE_PATTERN = "//a[contains(text(), '%s')]";
    private List<PostPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();


    public PostsPageTable(WebDriver driver) {
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
            tableRows.add(new PostPageTableRow(getAllRowsTitle().get(i).getText(), allAuthorTitle.get(i).getText(), getAllId().get(i).getAttribute("id")));
        }

        for(PostPageTableRow row: tableRows){
            try{
                driver.findElement(By.xpath(String.format(PATTERN, row.getId())));
                row.setDraft(true);
            }catch(NoSuchElementException e){
                row.setDraft(false);
                Logging.logWarn("Element not exists");
            }
        }
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
    public void deleteTableRows(){
        tableRows.clear();
    }

    @Override
    public void clickOnRowTitle(String rowTitle){
        for(PostPageTableRow row: tableRows){
            if(row.getName().equals(rowTitle)){
                driver.findElement(By.xpath(String.format(TITLE_PATTERN, rowTitle))).click();
                break;
            }
        }
    }

    public boolean isTitleDraft(String title){
        for(PostPageTableRow row: tableRows){
            if(row.getName().equals(title)){
                return row.isDraft();
            }
        }
        return false;
    }

    public PostPageTableRow getRowByTitle(String rowTitle){
        for(PostPageTableRow row : tableRows){
            if(row.getName().equals(rowTitle)){
                return row;
            }
        }
        return null;
    }
}
