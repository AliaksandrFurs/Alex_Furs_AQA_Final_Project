package elements.tables;

import elements.Table;
import elements.rows.PostPageTableRow;
import interfaces.tables.IPostsPageTableInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class PostsPageTable extends Table implements IPostsPageTableInterface {

    WebDriver driver;
    private static final String PATTERN = "//tr[@id='%s']//strong/span[contains(text(), 'Draft')]";
    private static final String TITLE_PATTERN = "//a[contains(text(), '%s')]";
    private List<PostPageTableRow> tableRows = new ArrayList<>();
    private List<WebElement> allAuthorTitle = new ArrayList<>();


    public PostsPageTable(WebDriver driver) {
        super();
        this.driver = driver;
        tableLocatorsMap.put("rowTitle", By.xpath("//a[contains(@class, 'row-title')]"));
        //rowTitle = By.xpath("//a[contains(@class, 'row-title')]");
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
        driver.findElement(tableLocatorsMap.get("rowCheckbox")).click();
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

    @Override
    public List<WebElement> getAllRowsTitle() {
        return allRowsTitle;
    }

    @Override
    public boolean isTitleDraft(String title){
        for(PostPageTableRow row: tableRows){
            if(row.getName().equals(title)){
                return row.isDraft();
            }
        }
        return false;
    }

    @Override
    public PostPageTableRow getRowByTitle(String rowTitle) {
            for(PostPageTableRow row : tableRows){
                if(row.getName().equals(rowTitle)){
                    return row;
                }
            }
            return null;
    }
}
