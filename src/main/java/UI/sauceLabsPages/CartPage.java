package UI.sauceLabsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartPage {
   private WebDriver driver;
   private WebDriverWait wait ;
   private By cartButton = By.xpath("//a[@class='shopping_cart_link']");
   private By CheckOutButton = By.xpath("//button[text()='Checkout']");
   //detail for proeed to order
    private By firstname  = By.xpath("//input[@id='first-name']");
    private By lastname = By.xpath("//input[@id='last-name']");
    private By postalCode = By.xpath("//input[@id='postal-code']");
    private By continueButton  = By.xpath("//input[@id='continue']");
    //paymen Setion
    private  By cartItemNamesAtPayment = By.xpath("//div[@class='cart_list']//div[@class='inventory_item_name']");
    private By countCartItems  = By.xpath("//div[@class='cart_list']//div[@class='cart_item']");
    private By getSubTotal  =  By.xpath("//div[@class='summary_subtotal_label']");
    private By getFrandteotal  = By.xpath("//div[@class='summary_total_label']");
    private By finishButton =  By.xpath("//button[@id='finish']");
    private By getTextConfiramtion  = By.xpath("//div[@id='checkout_complete_container']//h2"); //Thank you for your order!
    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver , Duration.ofSeconds(10));

    }

    public void gotoCart(){
        driver.findElement(cartButton).click();
    }
    public void goToCheckOut(){
        driver.findElement(CheckOutButton).click();
    }

    public void FillDetails(String name, String lastName , String PostalCode){
        driver.findElement(firstname).sendKeys(name);
        driver.findElement(lastname).sendKeys(lastName);
        driver.findElement(postalCode).sendKeys(PostalCode);

        driver.findElement(continueButton).click();
    }
    public int getCountCartItem (){
        return driver.findElements(countCartItems).size();
    }

    public List<String> getNamesOfItemsinCart(){
        List<WebElement> items = driver.findElements(cartItemNamesAtPayment);
        List<String>  itemNames  = new ArrayList<>();
        for(WebElement item :  items){
            itemNames.add(item.getText().trim());
        }
        return itemNames;
    }

    public List<String> getPaymentDetails() {
        List<String> paymentDetails = new ArrayList<>();

        paymentDetails.add(driver.findElement(getSubTotal).getText());
        paymentDetails.add(driver.findElement(getFrandteotal).getText());

        driver.findElement(finishButton).click();

        return paymentDetails;
    }






}

