package UI.ambTestLabPages;

import UI.ambTestLabUtils.TestDataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import UI.ambTestLabUtils.WebElementUtil;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElementUtil elementUtils;
    private By RegisterButton = By.xpath("//nav[@class='main-menu main-menu-two']//a[normalize-space(.)='Register']");
    private By firstName = By.id("firstName");
    private By middleName = By.id("middleName");
    private By lastName = By.id("lastName");
    private By Email = By.id("email");
    private By Password = By.id("password");
    private By eyeIconPassword = By.xpath("//div[@class='col-md-6']//button[@data-target='password']");
    private By confirmPassword = By.id("confirmPassword");
    private By eyeIconConfirmPassword = By.xpath("//div[@class='col-md-6']//button[@data-target='confirmPassword']");
    private By location = By.id("location");
    private By mobileNo = By.id("mobile");
    private By address = By.id("address");
    private By letsRegisterButton = By.xpath("//button[text()='Register']");


    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver ,Duration.ofSeconds(10));
        this.elementUtils = new WebElementUtil(driver);
    }


    public String[] fillRegisterForm() {
        String username = TestDataFactory.getEmail();
        String password = TestDataFactory.getPasswordF();
        elementUtils.click(RegisterButton);

        elementUtils.sendString(firstName, TestDataFactory.getFirstName());
        elementUtils.sendString(middleName, TestDataFactory.getMiddleName());
        elementUtils.sendString(lastName, TestDataFactory.getLastName());
        elementUtils.sendString(Email, username);
        elementUtils.sendString(Password, password);
        elementUtils.click(eyeIconPassword);
        elementUtils.sendString(confirmPassword, password);
        elementUtils.click(eyeIconConfirmPassword);
        elementUtils.sendString(mobileNo, TestDataFactory.getMobileNo());
        elementUtils.sendString(address, TestDataFactory.getAddress());
        elementUtils.sendString(location, TestDataFactory.getLocation());
        elementUtils.click(letsRegisterButton);

        return new String[]{username, password};
    }


    public void submitRegistration() {
        elementUtils.click(letsRegisterButton);
        wait.until(ExpectedConditions.urlContains("login"));
    }



}
