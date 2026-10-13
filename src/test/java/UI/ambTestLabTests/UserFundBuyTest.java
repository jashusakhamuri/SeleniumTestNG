        package UI.ambTestLabTests;

import UI.ambTestLabPages.UserFundBuy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.List;

public class UserFundBuyTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(UserFundBuyTest.class);

    @Test
    public void cartAddingAndPagination() {
        registerAndLogin();
        UserFundBuy fundPage = new UserFundBuy(getDriver());
        String[] cartNames = {"Emily Johnson", "James Davis", "Emma Miller", "Ava Taylor"};
        List<String> allCardNames = fundPage.collectNamesAcrossPages(1, 2 ,3 , 5,7,9,10,11);
        log.info("Collected card names: {}", allCardNames);
        for (String cartName : cartNames) {
            Assert.assertTrue(allCardNames.contains(cartName), "Card not found: " + cartName);
            UserFundBuy.FundDetails details = fundPage.getCartDetails(cartName);
            log.info("Fund: {} | Original price: {} | Selling price: {}", details.name, details.originalPrice, details.sellingPrice);
            Assert.assertFalse(details.originalPrice.isBlank(), "Original price should not be blank for " + cartName);
            Assert.assertFalse(details.sellingPrice.isBlank(), "Selling price should not be blank for " + cartName);
            fundPage.addToCart(cartName);
            if (cartName.equals("Ava Taylor")) fundPage.buyNow(cartName);


            log.info("Processed fund: {}", cartName);
        }
        String url = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("https://ambtestlab.netlify.app/portfolio.html")) ? driver.getCurrentUrl() : "";
        Assert.assertEquals(url, "https://ambtestlab.netlify.app/portfolio.html");



    }
}
