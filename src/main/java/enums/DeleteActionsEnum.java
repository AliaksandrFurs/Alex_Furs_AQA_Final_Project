package enums;

public enum DeleteActionsEnum {

    DELETE("delete"),
    TRASH("trash");

    private String value;

    DeleteActionsEnum(String value){this.value = value;}

    public String getValue(){return value;}
}
