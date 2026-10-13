package UI.ambTestLabTests;
import UI.ambTestLabPages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTestCases {
    private WebDriver driver;

@BeforeMethod
    public void Setup(){
    driver  = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://ambtestlab.netlify.app/");
}

@DataProvider(name="LoginData")
public  Object[][] LoginData(){
    return  new Object[][]{
            {"", "wrong123",},
            {"wrong@example.com", ""},
            {"wrong@example.com", "wrong123"},
            {"ambtestlab@ambmindspace.com", "test1234"}

    };


}

    @Test(dataProvider = "LoginData")
    public void verifyInvalidLoginToast(String username, String password) {
        LoginPage LP = new LoginPage(driver);
        LP.loginwithCred(username,password);
//        String message = LP.getToast();
//        Assert.assertEquals(message.trim(), "User not found");
    }

    @AfterMethod
    public void Teardown(){
    driver.close();
    }



}
