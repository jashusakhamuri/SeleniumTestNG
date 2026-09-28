package com.jashu.tests;

import com.jashu.pages.WebTablesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTablesTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyWebTable() {

        WebTablesPage webTablesPage =
                new WebTablesPage(driver);

        webTablesPage.goTo();

        webTablesPage.printHeaders();

        webTablesPage.printTableData();

        webTablesPage.ColumnsWiseData();
        webTablesPage.printFirstNames();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}