package UI.jashu.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {
                {"user1@gmail.com", "password123"},
                {"user2@gmail.com", "password456"},
                {"ambtestlab@ambmindspace.com", "test1234"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://ambtestlab.netlify.app/");

        // Click Login link
        WebElement loginButton =
                driver.findElement(By.xpath("//a[@href='/login']"));

        loginButton.click();

        // Enter username
        WebElement username =
                driver.findElement(By.id("email"));

        username.sendKeys(email);

        // Enter password
        WebElement passwordField =
                driver.findElement(By.id("password"));

        passwordField.sendKeys(password);

        // Click Login button
        WebElement loginBtn =
                driver.findElement(
                        By.xpath("//button[normalize-space()='Login']")
                );

        loginBtn.click();

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        driver.quit();
    }
}