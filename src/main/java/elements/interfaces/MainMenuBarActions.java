package elements.interfaces;

import enums.MainMenuBarSectionEnum;

public interface MainMenuBarActions {

    public void ClickOnBarSection(MainMenuBarSectionEnum sectionName);

    public boolean isSectionPresented(MainMenuBarSectionEnum sectionName);
}
