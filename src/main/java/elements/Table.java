package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Table {

    WebDriver driver;
    public int rowsNumber;
    private int columnsNUmber;

    public By rowTitle;
    public By authorTitle = By.xpath("//tbody[@id='the-list']//td[@class='author column-author']/a");


    public Table(WebDriver driver){

        this.driver = driver;


    }



}
