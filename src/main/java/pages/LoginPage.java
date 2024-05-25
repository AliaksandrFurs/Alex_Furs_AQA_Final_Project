package pages;

import elements.interfaces.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Wait;

public class LoginPage implements Page {

    WebDriver driver;
    private final static String LOGIN_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-login.php";

    private By loginInput = By.id("user_login");
    private By passwordInput = By.id("user_pass");
    private By submitButton = By.id("wp-submit");
    private By rememberMeCheckbox = By.id("rememberme");

    public LoginPage(WebDriver driver){

        this.driver = driver;
    }

    public void doOrdinaryLogin(String login, String password){

        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public void doRememberMeLogin(String login, String password){

        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(rememberMeCheckbox).click();
        driver.findElement(submitButton).click();

    }

    @Override
    public void openPage() {

        driver.get(LOGIN_URL);
        Wait.isElementPresented(driver.findElement(loginInput));
    }

    @Override
    public boolean isOpened() {

        Wait.isElementPresented(driver.findElement(loginInput));
        return true;
    }
}
