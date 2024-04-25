package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    private By loginInput = By.id("user_login");
    private By passwordInput = By.id("user_pass");
    private By submitButton = By.id("wp-submit");
    private By rememberMeCheckbox = By.id("rememberme");

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
}
