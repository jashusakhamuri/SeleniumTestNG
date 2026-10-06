//package UI.SauceLabsTests;
//
//import org.testng.annotations.Test;
//import UI.sauceLabsPages.ProductPage;
//import UI.sauceLabsPages.CartPage;
//
//import java.util.Arrays;
//
//public class CartTest  extends  BaseTest {
//    @Test
//    public void verifyCart(){
//        ProductPage  productPage  = new ProductPage(driver);
//        System.out.println(productPage.getTitle());
//        String[] itemsToAddToCart = {
//                "Sauce Labs Backpack",
//                "Sauce Labs Fleece Jacket",
//                "Sauce Labs Onesie"
//        };
//        for (String item : itemsToAddToCart) {
//            productPage.addToCart(item);
//        }
//
//        //cart Section
//        CartPage cartPage = new CartPage(driver);
//        cartPage.gotoCart();
//        System.out.println(productPage.getTitle());
//        System.out.println(Arrays.toString(productPage.getItemNameTexts()));
//        System.out.println(productPage.getItemNames());
//        System.out.println(productPage.getDescriptions());
//        cartPage.goToCheckOut();
//        System.out.println(productPage.getTitle());
//        cartPage.FillDetails("Jaswahu", "Sakhamuri", "522015");
//        System.out.println(productPage.getTitle());
//        System.out.println(cartPage.getCountCartItem());
//        System.out.println(cartPage.getNamesOfItemsinCart());
//        System.out.println(cartPage.getPaymentDetails());
//
//
//
//
//
//    }
//}
package UI.SauceLabsTests;

import UI.sauceLabsPages.CartPage;
import UI.sauceLabsPages.ProductPage;
//lOGGS IMPORT
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

//lISTENERS
import UI.SauceLabsTests.listeners.TestListener;
import org.testng.annotations.Listeners;

import java.util.List;
@Listeners(TestListener.class)
public class CartTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(CartTest.class);

    @Test
    public void verifyCart() {
        log.info("Starting Cart Test");

        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getTitle(), "Products");

        String[] itemsToAddToCart = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"};

        for (String item : itemsToAddToCart) {
            log.info("Adding product to cart: {}", item);
            productPage.addToCart(item);
        }

        CartPage cartPage = new CartPage(driver);
        cartPage.gotoCart();
        Assert.assertEquals(productPage.getTitle(), "Your Cart");

        List<String> expectedItems = List.of(itemsToAddToCart);
        Assert.assertEquals(cartPage.getNamesOfItemsinCart(), expectedItems);
        Assert.assertEquals(cartPage.getCountCartItem(), itemsToAddToCart.length);

        cartPage.goToCheckOut();
        Assert.assertEquals(productPage.getTitle(), "Checkout: Your Information");

        cartPage.FillDetails("Jaswanth", "Sakhamuri", "522015");
        Assert.assertEquals(productPage.getTitle(), "Checkout: Overview");

        Assert.assertEquals(cartPage.getCountCartItem(), 3);
        Assert.assertEquals(cartPage.getNamesOfItemsinCart(), expectedItems);

        List<String> paymentDetails = cartPage.getPaymentDetails();
        log.info("Payment details: {}", paymentDetails);

        Assert.assertEquals(paymentDetails, List.of("Item total: $87.97", "Total: $95.01"));

        log.info("Cart Test completed successfully");
    }
}