package com.jashu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public SelectPage(WebDriver driver){
          this.driver = driver;
          this.wait  = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    private By  SelectValue  = By.xpath("//input[@id='react-select-2-input']");
    private By SelectOne  = By.xpath("//div[@id='selectOne']//div[contains(@class,'control')]");
    private By oldStyleSelect  = By.id("oldSelectMenu");
    private By Multiselectdropdown = By.xpath("//div[contains(@class,'control')]//input[@id='react-select-4-input']");

    public void Navigate(){
        driver.get("https://demoqa.com/select-menu");
    }

    // Select Value - React Select
    public void SelectValue(String value){
        driver.findElement(SelectValue).click();
        driver.findElement(SelectValue).sendKeys(value);
        driver.findElement(By.xpath("//div[text()='"+ value +"']")).click();

    }

//    public void SelectOne(String value2){
//        driver.findElement(SelectOne).click();
//        driver.findElement(By.xpath("div[text()='"+value2 +"'"));
//
//
//    }
    public void SelectOne(String value2) {
        wait.until(ExpectedConditions.elementToBeClickable(SelectOne)).click();

        By option = By.xpath(("//div[text()='"+value2+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    public void OldStyle(String Value3){
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(oldStyleSelect));
        Select select = new Select(dropdown);
        select.selectByVisibleText(Value3);
    }
    public void multipleSelect(String value4){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(Multiselectdropdown)).click();
        driver.findElement(Multiselectdropdown).sendKeys(value4);
        By option  = By.xpath("//div[contains(@class,'option') and text()='" + value4 + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(option)).click();

    }











}


