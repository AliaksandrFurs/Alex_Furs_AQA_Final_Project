package enums;

public enum MainMenuBarSectionEnum {
    POSTS("Posts"),
    MEDIA("Media"),
    PAGES("Pages");

    private String value;

    MainMenuBarSectionEnum(String value){this.value = value;}

    public String getValue(){return value;}

}
