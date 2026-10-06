package UI.jashu.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Set;


public class BrowserWindowsTest {


     @Test
       public void  handelNewTab(){
         WebDriver driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.get("https://demoqa.com/browser-windows");
         WebElement newtab = driver.findElement(By.id("tabButton"));
         WebElement messageWindowButton  = driver.findElement(By.id("messageWindowButton"));

         WebElement NewWindow  = driver.findElement(By.id("windowButton"));
         String originalWindow = driver.getWindowHandle();
         System.out.println("Original Window Handle: " + originalWindow);

         newtab.click();


         newtab.click();
         NewWindow.click();
         NewWindow.click();
//         messageWindowButton.click();
         Set<String> allWindows = driver.getWindowHandles();
         System.out.println("All Window Handles:");
         for (String window : allWindows){
             System.out.println("Window Handle: " + window);
         }
         System.out.println("=================================");
         for (String window : allWindows){
             driver.switchTo().window(window);

             System.out.println("Switched to:");
             System.out.println("Handle : " + window);
             System.out.println("Title  : " + driver.getTitle());
             System.out.println("URL    : " + driver.getCurrentUrl());
//             if (!window.equals(originalWindow)) {
//                 System.out.println(driver.findElement(By.id("sampleHeading")).getText());
//             }


             System.out.println("---------------------------------");
             // Close only NEW tabs
             if (!window.equals(originalWindow)) {
                 driver.close();
             }

         }
         driver.switchTo().window(originalWindow);





         // Now we are in new tab
         System.out.println("New Tab Title: " + driver.getTitle());
     }


}
