package com.jashu.tests;

import com.jashu.pages.AmazonPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest {

    WebDriver driver;

    AmazonPage amazonPage;


    // =========================
    // Setup
    // =========================

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        driver.manage()
                .window()
                .maximize();

        amazonPage =
                new AmazonPage(driver);
    }


    // =========================
    // Test
    // =========================

    @Test
    public void searchProduct() {

        amazonPage.goTo();

        amazonPage.searchAndClick(
                "laptop",
                "laptop for gaming"
        );
       amazonPage.getProductDetails("ASUS Vivobook S14,Smartchoice,AMD Ryzen AI 5 330,16GB/512GB (Upgradeable),OLED,14\",Win11,Office24,M365 Basic (1Y),Silver,1.4Kg,M3407KA-SF2501WS,50 Tops,Metallic Design,Next-Gen AI Laptop,Copilot+");

        amazonPage.getNavLinks();

    }




    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}