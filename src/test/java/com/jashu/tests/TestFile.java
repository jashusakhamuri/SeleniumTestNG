package com.jashu.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class TestFile {
    @Test
    public void openGoogle() {

        WebDriver driver = new ChromeDriver() ;
        driver.get("https://demoqa.com/automation-practice-form");
        System.out.println("Tittle "+driver.getTitle());


      driver.quit();


    }
}
