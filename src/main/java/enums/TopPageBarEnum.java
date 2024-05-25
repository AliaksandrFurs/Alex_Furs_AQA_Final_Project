package enums;

public enum TopPageBarEnum {

    WORDPRESS("WordPress on Azure"),
    RELOAD("Reload"),
    LIKES("Likes"),
    NEW("New"),
    PERFORMANCE("Performance"),
    ADMIN("Admin");

    private String value;

    TopPageBarEnum(String value){this.value = value;}

    public String getValue(){return value;}
}
