package interfaces.pages;

import enums.MainMenuBarSectionEnum;

public interface IMainPageInterface {

    void openPage();

    boolean isOpened();

    void ClickOnBarSection(MainMenuBarSectionEnum sectionName);

    boolean isSectionPresented(MainMenuBarSectionEnum sectionName);
}
