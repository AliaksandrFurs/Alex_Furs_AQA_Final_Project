package interfaces.pages;

import enums.MainMenuBarSectionEnum;

public interface IPostsOrdinaryPageInterface {

    void openPage();

    boolean isOpened();

    void searchEntity(String entityName);

    boolean isEntityAvailable(String entityName);

    void deleteEntity(String enityName);

    void openAddingEntityPage();

    void clickOnEntity(String entityName);

    void ClickOnBarSection(MainMenuBarSectionEnum sectionName);

    boolean isSectionPresented(MainMenuBarSectionEnum sectionName);

    boolean isPostDraft(String postTitle);

    boolean isEntityWasUpdate(String entityName);
}
