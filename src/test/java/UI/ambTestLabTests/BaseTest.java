package UI.ambTestLabTests;

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

import UI.ambTestLabPages.LoginPage;
import UI.ambTestLabPages.RegisterPage;
import UI.ambTestLabUtils.ConfigReader;
import UI.ambTestLabUtils.DriverFactory;
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
        driver.get(ConfigReader.get("base.url"));
        // loginPage.loginwithCred(ConfigReader.get("username"), ConfigReader.get("password"));
    }
    // userRegisted
    protected void loginAs(String username, String password) {
        loginPage.loginwithCred(username, password);
    }
    protected void loginAsAdmin() {
        loginAs(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    protected void loginAsUser(String RegisterUserName , String RegisterPassword) {
        loginPage.loginwithUserCred(RegisterUserName , RegisterPassword);
    }

    protected String[] registerNewUser() {
        RegisterPage registerPage = new RegisterPage(driver);   // created here, not in the test
        String[] creds = registerPage.fillRegisterForm();
        registerPage.submitRegistration();
        return creds;
    }

    protected void registerAndLogin() {
        String[] creds = registerNewUser();
        loginAsUser(creds[0], creds[1]);
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
