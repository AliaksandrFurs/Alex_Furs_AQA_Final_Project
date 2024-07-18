package pages;

import enums.LocatorsEnum;
import interfaces.pages.ILoginPageInterface;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Wait;
import static enums.LocatorsEnum.*;
import java.util.HashMap;

public class LoginPage implements ILoginPageInterface {

    WebDriver driver;
    private final static String LOGIN_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-login.php";
    private HashMap<LocatorsEnum, By> pageLocatorsMap;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        pageLocatorsMap = new HashMap<>();
        pageLocatorsMap.put(LOGININPUTLOCATOR, By.id("user_login"));
        pageLocatorsMap.put(PASSWORDINPUTLOCATOR, By.id("user_pass"));
        pageLocatorsMap.put(SUBMITLOGINBUTTONLOCATOR, By.id("wp-submit"));
        pageLocatorsMap.put(REMEMBERMECHECKBOXLOCATOR, By.id("rememberme"));
        pageLocatorsMap.put(INVALIDLOGINLABELLOCATOR, By.id("login_error"));
        pageLocatorsMap.put(VISIBLEPASSWORDLOCATOR, By.xpath("//*[@class='button button-secondary wp-hide-pw hide-if-no-js']"));
    }

    @Step("Login to wordpress using credentiales")
    public void doLogin(String login, String password, boolean isLoginRemember){
        driver.findElement(pageLocatorsMap.get(LOGININPUTLOCATOR)).clear();
        driver.findElement(pageLocatorsMap.get(LOGININPUTLOCATOR)).sendKeys(login);
        driver.findElement(pageLocatorsMap.get(PASSWORDINPUTLOCATOR)).sendKeys(password);
        if(isLoginRemember == true){
            driver.findElement(pageLocatorsMap.get(REMEMBERMECHECKBOXLOCATOR)).click();
        }
        driver.findElement(pageLocatorsMap.get(SUBMITLOGINBUTTONLOCATOR)).click();

    }

    @Step("Open login page")
    public void openPage() {
        driver.get(LOGIN_URL);
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(LOGININPUTLOCATOR)));
    }

    @Step("Verify is login page opened successfully")
    public boolean isOpened() {
        Wait.isElementPresented(driver.findElement(pageLocatorsMap.get(INVALIDLOGINLABELLOCATOR)));
        return true;
    }

    public boolean isPasswordMasked(String password){
        driver.findElement(pageLocatorsMap.get(LOGININPUTLOCATOR)).clear();
        driver.findElement(pageLocatorsMap.get(PASSWORDINPUTLOCATOR)).sendKeys(password);
        driver.findElement(pageLocatorsMap.get("visiblePassword")).click();
        if(driver.findElement(pageLocatorsMap.get(PASSWORDINPUTLOCATOR)).getAttribute("type").equals("text")){
            return true;
        }else{
            return false;
        }
    }
}
