package dataproviders;

import enums.MainMenuBarSectionEnum;
import enums.TopPageBarEnum;
import org.testng.annotations.DataProvider;

public class MainPageTestDataProvider {

    @DataProvider(name = "top bar elements")
    public Object [][] getTopBarValues(){

        return new Object[][] {
                {TopPageBarEnum.ADMIN},
                {TopPageBarEnum.LIKES},
                {TopPageBarEnum.PERFORMANCE},
                {TopPageBarEnum.RELOAD}
        };
    }

    @DataProvider(name = "main menu bar elements")
    public Object [][] getMainMenuValues(){

        return new Object [][]{
                {MainMenuBarSectionEnum.PAGES, "Pages"},
                {MainMenuBarSectionEnum.MEDIA, "Media"},
                {MainMenuBarSectionEnum.POSTS, "Posts"},
        };
    }
}
