package UI.ambTestLabTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import UI.ambTestLabPages.RegisterPage;
import UI.ambTestLabPages.LoginPage;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends  BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    private RegisterPage registerPage;

    @Test
    public void verifyUrl() {
        loginAsAdmin();

        String url = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("https://ambtestlab.netlify.app/admin-panel.html")) ? driver.getCurrentUrl() : "";
        Assert.assertEquals(url, "https://ambtestlab.netlify.app/admin-panel.html");
    }
    @Test
    public void RegisterandLogin(){
        registerAndLogin();

        String url = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("https://ambtestlab.netlify.app/funds.html")) ? driver.getCurrentUrl() : "";
        Assert.assertEquals(url, "https://ambtestlab.netlify.app/funds.html");

    }




}
