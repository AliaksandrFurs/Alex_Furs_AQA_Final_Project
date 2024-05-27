package elements.interfaces;

import enums.AddingEntityTypeEnum;
import org.openqa.selenium.By;

public interface BasicCreatePageActions {

    By addTitleField = By.xpath("//h1[contains(@class, 'wp-block wp-block-post-title block-editor-block-list__block editor-post-title editor-post-title__input rich-text')]");
    By addPostBodyField = By.xpath("//p[contains(@class, 'block-editor-default-block-appender__content')]");
    By existingBodyField = By.xpath("//p[contains(@class, 'block-editor-rich-text__editable block-editor-block-list__block wp-block wp-block-paragraph rich-text')]");
    By publishButton = By.xpath("//button[contains(@class, 'components-button editor-post-publish-button editor-post-publish-button__button is-primary')]");
    By publishSnackBar = By.xpath("//div[contains(@class, 'components-snackbar-list components-editor-notices__snackbar')]");
    By dashboardLogo = By.xpath("//a[contains(@class, 'components-button edit-post-fullscreen-mode-close')]");
    By saveDraftButton = By.xpath("//button[contains(@class, 'components-button editor-post-save-draft is-tertiary')]");

    void openPage(AddingEntityTypeEnum entityType);

    boolean isOpened();

    void dashboardClick();

    void addNewEntity(String postTitle, String postBody);

    void saveEntityAsDraft(String postTitle, String postBody);

}
