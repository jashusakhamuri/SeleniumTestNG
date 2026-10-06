//package UI.SauceLabsTests;
//import UI.sauceLabsPages.LoginPage;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class LoginTest {
//
//    WebDriver driver;
//    LoginPage loginpage;
//    @Test
//    public void setup(){
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        loginpage = new LoginPage(driver);
//        loginpage.goTo();
//        loginpage.login();
//    }
//
//
//
//
//}

package UI.SauceLabsTests;

import UI.sauceLabsPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLogin() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
