//package UI.sauceLabsPages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class LoginPage {
//    private WebDriver driver ;
//    private WebDriverWait wait;
//    private By username  = By.xpath("//input[@placeholder='Username']");
//    private By password = By.xpath("//input[@placeholder='Password']");
//    private By submitButton = By.xpath("//input[@type='submit']");
//
//    public LoginPage(WebDriver driver){
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//    public void goTo(){
//
//        driver.get("https://www.saucedemo.com/");
//    }
//    public void login(String usernames  , String Passwords){
//        driver.findElement(username).sendKeys(usernames);
//        driver.findElement(password).sendKeys(Passwords);
//        driver.findElement(submitButton).click();
//    }
//
//
//
//
//}
package UI.sauceLabsPages;

import UI.sauceLabsUtils.ConfigReader;
import UI.sauceLabsUtils.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private WebElementUtils elementUtils;

    private By username = By.xpath("//input[@placeholder='Username']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By submitButton = By.xpath("//input[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new WebElementUtils(driver);
    }

    public void goTo() {
        driver.get(ConfigReader.get("base.url"));
    }

    public void login(String usernames, String passwords) {
        elementUtils.type(username, usernames);
        elementUtils.type(password, passwords);
        elementUtils.click(submitButton);
    }
}