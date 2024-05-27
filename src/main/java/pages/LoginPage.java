package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Wait;

public class LoginPage {

    WebDriver driver;
    private final static String LOGIN_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-login.php";

    private By loginInput = By.id("user_login");
    private By passwordInput = By.id("user_pass");
    private By submitButton = By.id("wp-submit");
    private By rememberMeCheckbox = By.id("rememberme");
    private By invalidLoginLabel = By.id("login_error");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Login to wordpress using credentiales")
    public void doLogin(String login, String password, boolean isLoginRemember){
        driver.findElement(loginInput).clear();
        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        if(isLoginRemember == true){
            driver.findElement(rememberMeCheckbox).click();
        }
        driver.findElement(submitButton).click();

    }

    @Step("Open login page")
    public void openPage() {
        driver.get(LOGIN_URL);
        Wait.isElementPresented(driver.findElement(loginInput));
    }

    @Step("Verify is login page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(invalidLoginLabel));
        return true;
    }
}
