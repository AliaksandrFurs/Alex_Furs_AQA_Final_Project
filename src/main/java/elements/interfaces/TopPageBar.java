package elements.interfaces;

import enums.MainMenuBarSectionEnum;
import enums.TopPageBarEnum;
import org.openqa.selenium.WebElement;

public interface TopPageBar {

    public void ClickOnBarSection(TopPageBarEnum sectionName);

    public boolean isSectionPresented(TopPageBarEnum sectionName);
}
