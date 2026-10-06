package UI.jashu.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewRegistrationForm {

    @DataProvider(name = "studentData")
    public Object[][] studentData() {

        return new Object[][]{
                {
                        "Jaswanth",
                        "Sakhamuri",
                        "jaswanth@test.com",
                        "9876543210",
                        "Guntur, Andhra Pradesh"
                },
                {
                        "Rahul",
                        "Kumar",
                        "rahul@test.com",
                        "9876543211",
                        "Hyderabad, Telangana"
                },
                {
                        "John",
                        "Smith",
                        "john@test.com",
                        "9876543212",
                        "Bangalore, Karnataka"
                }
        };
    }


    @Test(dataProvider = "studentData")
    public void studentRegistration(
            String firstNameData,
            String lastNameData,
            String emailData,
            String mobileData,
            String addressData) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Open application
        driver.get("https://demoqa.com/automation-practice-form");


        // First Name
        WebElement firstName =
                driver.findElement(By.id("firstName"));

        firstName.sendKeys(firstNameData);

        Assert.assertEquals(
                firstName.getAttribute("value"),
                firstNameData,
                "First Name was not entered correctly"
        );


        // Last Name
        WebElement lastName =
                driver.findElement(By.id("lastName"));

        lastName.sendKeys(lastNameData);

        Assert.assertEquals(
                lastName.getAttribute("value"),
                lastNameData,
                "Last Name was not entered correctly"
        );


        // Email
        WebElement email =
                driver.findElement(By.id("userEmail"));

        email.sendKeys(emailData);

        Assert.assertEquals(
                email.getAttribute("value"),
                emailData,
                "Email was not entered correctly"
        );


        // Gender
        WebElement maleGender =
                driver.findElement(By.id("gender-radio-1"));

        maleGender.click();

        Assert.assertTrue(
                maleGender.isSelected(),
                "Male gender was not selected"
        );


        // Hobbies
        WebElement hobbiesSports =
                driver.findElement(By.id("hobbies-checkbox-1"));

        hobbiesSports.click();

        Assert.assertTrue(
                hobbiesSports.isSelected(),
                "Sports was not selected"
        );


        // Mobile
        WebElement mobile =
                driver.findElement(By.id("userNumber"));

        mobile.sendKeys(mobileData);

        Assert.assertEquals(
                mobile.getAttribute("value"),
                mobileData,
                "Mobile number was not entered correctly"
        );


        // Address
        WebElement address =
                driver.findElement(By.id("currentAddress"));

        address.sendKeys(addressData);

        Assert.assertEquals(
                address.getAttribute("value"),
                addressData,
                "Address was not entered correctly"
        );


        // Visibility assertions
        Assert.assertTrue(
                firstName.isDisplayed(),
                "First Name field is not displayed"
        );

        Assert.assertTrue(
                lastName.isDisplayed(),
                "Last Name field is not displayed"
        );

        Assert.assertTrue(
                email.isDisplayed(),
                "Email field is not displayed"
        );

        Assert.assertTrue(
                mobile.isDisplayed(),
                "Mobile field is not displayed"
        );

        Assert.assertTrue(
                address.isDisplayed(),
                "Address field is not displayed"
        );


        System.out.println("Registration completed for: " + firstNameData);

        driver.quit();
    }
}