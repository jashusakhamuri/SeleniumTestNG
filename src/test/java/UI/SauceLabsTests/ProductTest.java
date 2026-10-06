//package UI.SauceLabsTests;
//
//import UI.sauceLabsPages.ProductPage;
//import org.testng.annotations.Test;
//
//import java.util.Arrays;
//
//public class ProductTest extends BaseTest {
//
//    @Test
//    public void verifyProducts() {
//        ProductPage productPage = new ProductPage(driver);
//
//        System.out.println(productPage.getTitle());
//        System.out.println(productPage.getItemCount());
//        System.out.println(Arrays.toString(productPage.getItemNameTexts()));
//        System.out.println(productPage.getItemNames());
//        System.out.println(productPage.getDescriptions());
//
//        String[] itemsToAddToCart = {"Sauce Labs Backpack" , "Sauce Labs Fleece Jacket" , "Sauce Labs Onesie"};
//        for(String item: itemsToAddToCart){
//            productPage.addToCart(item);
//        }
//
//    }
//
//}

package UI.SauceLabsTests;

import UI.sauceLabsPages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void verifyProducts() {
        ProductPage productPage = new ProductPage(driver);

        Assert.assertEquals(productPage.getTitle(), "Products");
        Assert.assertEquals(productPage.getItemCount(), 6);
    }

    @Test
    public void addProductsToCart() {
        ProductPage productPage = new ProductPage(driver);

        String[] itemsToAddToCart = {
                "Sauce Labs Backpack",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

        for (String item : itemsToAddToCart) {
            productPage.addToCart(item);
        }
    }
}