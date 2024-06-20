package interfaces;

import enums.MainMenuBarSectionEnum;
import pages.BasePage;

public interface IMainMenuBarActions {

    BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName);

    boolean isSectionPresented(MainMenuBarSectionEnum sectionName);

    void setExpandedStatus();
}
