package interfaces;

import enums.MainMenuBarSectionEnum;
import pages.BasePage;

public interface MainMenuBarActions {

    BasePage ClickOnBarSection(MainMenuBarSectionEnum sectionName);

    boolean isSectionPresented(MainMenuBarSectionEnum sectionName);
}
