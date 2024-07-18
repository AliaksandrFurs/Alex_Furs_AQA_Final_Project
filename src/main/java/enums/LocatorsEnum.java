package enums;

public enum LocatorsEnum {

    PAGENAMELOCATOR("pageNameLocator"),
    NOENTITYFOUNDLOCATOR("noEntityFoundLocator"),
    ROWCHECKBOXLOCATOR("rowCheckboxLocator"),
    SITEHEALTHSTATUSLOCATOR("siteHealthLocator"),
    ADDTITLEFIELDLOCATOR("addTitleFieldLocator"),
    ADDPOSTBODYFIELSLOCATOR("addPostBodyFieldLocator"),
    EXISTINGBODYFIELDLOCATOR("existingBodyFieldLocator"),
    PUBLISHBUTTONLOCATOR("publishButtonLocator"),
    PUBLISHSNACKBARLOCATOR("publishSnackBarLocator"),
    DASHBOARDLOGOLOCATOR("dashboardLogoLocator"),
    SAVEDRAFTBUTTONLOCATOR("saveDraftButtonLocator"),
    IFRAMELOCATOR("iframeLocator"),
    LOGININPUTLOCATOR("loginInputLocator"),
    PASSWORDINPUTLOCATOR("passwordInputLocator"),
    SUBMITLOGINBUTTONLOCATOR("submitButtonLocator"),
    REMEMBERMECHECKBOXLOCATOR("rememberMeCheckboxLocator"),
    INVALIDLOGINLABELLOCATOR("invalidLoginLableLocator"),
    VISIBLEPASSWORDLOCATOR("visiblePasswordLocator"),
    TABLELOCATOR("table"),
    ROWIDLOCATOR("rowIdLocator"),
    SEARCHINPUTLOCATOR("serachInput"),
    ADDNEWENTITYBUTTONLOCATOR("addNewEntityButtonLocator"),
    SEARCHBUTTONLOCATOR("searchButtonLocator"),
    APPLYACTIONBUTTONLOCATOR("applyActioinButtonLocator"),
    DELETEACTIONSDROPDOWNLOCATOR("deleteActionsDropdownLocator"),
    TITLELOCATOR("titleLocator"),
    AUTHORNAMELOCATOR("authorNameLocator"),
    DATELOCATOR("dateLocator"),
    DRAFTLOCATOR("draftLocator"),
    COMMENTLOCATOR("commentLocator"),
    SUBMITUPLOADBUTTONLOCATOR("submitButtonLocator"),
    UPLOADBUTTONLOCATOR("uploadButtonLocator"),
    ERRORUPLOADLOCATOR("errorUploadLocator");

    private String value;

    LocatorsEnum(String value){this.value = value;}

    public String getValue(){return value;}
}
