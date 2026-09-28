package com.jashu.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Alerts {
    @Test
    public void Alertss(){
        WebDriver driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");

        WebElement okalert   = driver.findElement(By.id("alertButton"));
        WebElement confirmBOx  = driver.findElement(By.id("confirmButton"));
        WebElement promptBOx = driver.findElement(By.id("promtButton"));
        String originalWindow  = driver.getWindowHandle();
        okalert.click();


        Alert alert  = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        driver.switchTo().window(originalWindow);
        System.out.println(driver.getTitle());
        driver.close();

    }

}
