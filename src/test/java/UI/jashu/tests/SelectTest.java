package UI.jashu.tests;
import UI.jashu.pages.SelectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectTest {
    private WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public  void SelectClass(){
        SelectPage obj1 = new SelectPage(driver);
        obj1.Navigate();
        obj1.SelectValue("Group 1, option 1");
        obj1.SelectOne("Dr.");
        obj1.OldStyle("Black");
        obj1.multipleSelect("Green");
        obj1.multipleSelect("Red");
    }
    @AfterMethod
    public void close(){
        driver.quit();
    }



}
