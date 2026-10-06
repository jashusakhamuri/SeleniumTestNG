//package UI.SauceLabsTests;
//
//import UI.sauceLabsPages.LoginPage;
//import UI.sauceLabsUtils.ConfigReader;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//
//import java.util.Map;
//
//public class BaseTest {
//
//    protected WebDriver driver;
//    protected LoginPage loginPage;
//
//    @BeforeMethod
//    public void setup() {
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", Map.of("credentials_enable_service", false, "profile.password_manager_leak_detection", false));
//
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//
//        loginPage = new LoginPage(driver);
//        loginPage.goTo();
//        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
//}

package UI.SauceLabsTests;

import UI.sauceLabsPages.LoginPage;
import UI.sauceLabsUtils.ConfigReader;
import UI.sauceLabsUtils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {
        DriverFactory.createDriver();
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}