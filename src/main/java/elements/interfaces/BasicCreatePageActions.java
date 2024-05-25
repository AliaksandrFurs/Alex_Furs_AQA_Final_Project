package elements.interfaces;

import enums.AddingEntityTypeEnum;
import org.openqa.selenium.By;

public interface BasicCreatePageActions {

    By addTitleField = By.xpath("//h1[contains(@class, 'wp-block wp-block-post-title block-editor-block-list__block editor-post-title editor-post-title__input rich-text')]");
    By addPostBodyField = By.xpath("//p[contains(@class, 'block-editor-default-block-appender__content')]");
    By publishButton = By.xpath("//button[contains(text(),'Publish')]");
    By publishSnackBar = By.xpath("//div[contains(@class, 'components-snackbar-list components-editor-notices__snackbar')]");
    By dashboardLogo = By.xpath("//a[contains(@class, 'components-button edit-post-fullscreen-mode-close')]");
    By saveDraftButton = By.xpath("//button[contains(@class, 'components-button editor-post-save-draft is-tertiary')]");

    public void openPage(AddingEntityTypeEnum entityType);

    public boolean isOpened();

    void dashboardClick();

    void addNewEntity(String postTitle, String postBody);

    void saveEntityAsDraft(String postTitle, String postBody);

}
