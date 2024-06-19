package interfaces.pages;

public interface ILoginPageInterface {

    void doLogin(String login, String password, boolean isLoginRemember);

    void openPage();

    boolean isOpened();

    boolean isPasswordMasked(String password);
}
