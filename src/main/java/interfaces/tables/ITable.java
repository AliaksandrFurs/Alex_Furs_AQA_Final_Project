package interfaces.tables;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface ITable {

    void createTableRows();

    void deleteTableRows();

    void updateRowsNumber();

    void selectRows();

    void clickOnRowTitle(String rowTitle);

    List<WebElement> getAllRowsTitle();

    int getRowsNumber();
}
