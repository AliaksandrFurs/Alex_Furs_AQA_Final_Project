package enums;

public enum AddingEntityTypeEnum {

    PAGE("Page"),
    POST("Post");

    private String value;

    AddingEntityTypeEnum(String value){this.value = value;}

    public String getValue(){return value;}
}
