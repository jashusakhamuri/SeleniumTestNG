package UI.ambTestLabPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import UI.ambTestLabUtils.WebElementUtil;
import org.openqa.selenium.support.ui.WebDriverWait;


import UI.ambTestLabUtils.ConfigReader;
//logs import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait  wait;
    private WebElementUtil elementUtils;
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    private By username  = By.id("email");
    private By password  =  By.id("password");
    private By loginButton = By.xpath("//nav[@class='main-menu main-menu-two']//a[normalize-space(.)='Login']");
    private By submitButton = By.xpath("//form[@id='loginForm']//button[@type='submit']");
    private By  eyeIcon = By.xpath("//form[@id='loginForm']//button[@data-target='password']");
    private By toast  = By.id("toastContainer");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.elementUtils = new WebElementUtil(driver);


    }


    public String[] loginwithCred(String name , String Password){
        elementUtils.click(loginButton);
        log.info("Entering password");
        elementUtils.sendString(username , name);
        elementUtils.sendString(password , Password);
        log.info("Entering password");
        elementUtils.click(eyeIcon);
        String usernameDetail = elementUtils.getInputValue(username);

        String  passwordDetail = elementUtils.getInputValue(password);

        String[] userDetails   = {usernameDetail ,passwordDetail};
        elementUtils.click(submitButton);
        log.info("User details: {}", Arrays.toString(userDetails));

        return userDetails;
    }
    public String verifyUrl(){
      String currenturl  = driver.getCurrentUrl();
      log.info(currenturl);
      return currenturl;

    }

    public String  getToast() {
         return elementUtils.getToastMessage(toast);

    }

    public String[] loginwithUserCred(String name , String Password){

        log.info("Entering password");
        elementUtils.sendString(username , name);
        elementUtils.sendString(password , Password);
        log.info("Entering password");
        elementUtils.click(eyeIcon);
        String usernameDetail = elementUtils.getInputValue(username);

        String  passwordDetail = elementUtils.getInputValue(password);

        String[] userDetails   = {usernameDetail ,passwordDetail};
        elementUtils.click(submitButton);
        log.info("User details: {}", Arrays.toString(userDetails));

        return userDetails;

    }
}
