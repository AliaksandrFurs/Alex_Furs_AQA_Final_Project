package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Browser;

public class Table {

    //WebDriver driver;
    public int rowsNumber;
    private int columnsNUmber;

    public By rowTitle;
    public By authorTitle = By.xpath("//tbody[@id='the-list']//td[@class='author column-author']/a");


}
