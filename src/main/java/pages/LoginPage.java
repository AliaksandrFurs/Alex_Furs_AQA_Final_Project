package pages;

import interfaces.pages.ILoginPageInterface;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Wait;

import java.util.HashMap;

public class LoginPage implements ILoginPageInterface {

    WebDriver driver;
    private final static String LOGIN_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-login.php";
    private HashMap<String, By> pageLocatorsMap;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        pageLocatorsMap = new HashMap<>();
        pageLocatorsMap.put("loginInput", By.id("user_login"));
        pageLocatorsMap.put("passwordInput", By.id("user_pass"));
        pageLocatorsMap.put("submitButton", By.id("wp-submit"));
        pageLocatorsMap.put("rememberMeCheckbox", By.id("rememberme"));
        pageLocatorsMap.put("invalidLoginLabel", By.id("login_error"));
        pageLocatorsMap.put("visiblePassword", By.xpath("//*[@class='button button-secondary wp-hide-pw hide-if-no-js']"));
    }

    @Step("Login to wordpress using credentiales")
    public void doLogin(String login, String password, boolean isLoginRemember){
        driver.findElement(pageLocatorsMap.get("loginInput")).clear();
        driver.findElement(pageLocatorsMap.get("loginInput")).sendKeys(login);
        driver.findElement(pageLocatorsMap.get("passwordInput")).sendKeys(password);
        if(isLoginRemember == true){
            driver.findElement(pageLocatorsMap.get("rememberMeCheckbox")).click();
        }
        driver.findElement(pageLocatorsMap.get("submitButton")).click();

    }

    @Step("Open login page")
    public void openPage() {
        driver.get(LOGIN_URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("loginInput")));
    }

    @Step("Verify is login page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get("invalidLoginLabel")));
        return true;
    }

    public boolean isPasswordMasked(String password){
        driver.findElement(pageLocatorsMap.get("loginInput")).clear();
        driver.findElement(pageLocatorsMap.get("passwordInput")).sendKeys(password);
        driver.findElement(pageLocatorsMap.get("visiblePassword")).click();
        if(driver.findElement(pageLocatorsMap.get("passwordInput")).getAttribute("type").equals("text")){
            return true;
        }else{
            return false;
        }
    }
}
