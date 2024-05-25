package elements;

import org.openqa.selenium.By;


public class Table {

    //WebDriver driver;
    public int rowsNumber;
    private int columnsNUmber;

    public By rowTitle;
    public By authorTitle = By.xpath("//tbody[@id='the-list']//td[@class='author column-author']/a");
    public By rowCheckbox = By.xpath("//input[contains(@id, 'cb-select')]");
    public By rowId = By.xpath("//tr[contains(@id, 'post-')]");


}
