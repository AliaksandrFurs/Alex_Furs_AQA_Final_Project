package interfaces.pages;

import enums.AddingEntityTypeEnum;

public interface ICreatePageInterface {

    void openPage(AddingEntityTypeEnum entityType);

    boolean isOpened();

    void dashboardClick();

    void addNewEntity(String postTitle, String postBody);

    void saveEntityAsDraft(String postTitle, String postBody);

    void updateEntity(String newPostTitle, String newPostBody);
}
