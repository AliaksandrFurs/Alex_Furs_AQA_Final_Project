package elements.interfaces;

import enums.MainMenuBarSectionEnum;
import pages.BasePage;

public interface MainMenuBarActions {

    public BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName);

    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName);
}
