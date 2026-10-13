
package UI.ambTestLabTests;

import UI.ambTestLabPages.UserFundBuy;
import UI.ambTestLabPages.UserPortfiloPage;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class UserPorffiloTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(UserPorffiloTest.class);

    @Test
    public void cartList() {
        String[] creds = registerNewUser();
        Assert.assertNotNull(creds, "User registration credentials should not be null");
        loginAsUser(creds[0], creds[1]);

        UserFundBuy fundPage = new UserFundBuy(getDriver());
        UserPortfiloPage profilePage = new UserPortfiloPage(getDriver());
        String[] cartNames = {"Emily Johnson", "James Davis", "Emma Miller", "Ava Taylor"};

        for (String cartName : cartNames) {
            UserFundBuy.FundDetails details = fundPage.getCartDetails(cartName);
            Assert.assertNotNull(details, "Fund details should not be null for " + cartName);
            Assert.assertFalse(details.originalPrice.isBlank(), "Original price is missing for " + cartName);
            Assert.assertFalse(details.sellingPrice.isBlank(), "Selling price is missing for " + cartName);
            fundPage.addToCart(cartName);
            if (cartName.equals("Ava Taylor")) fundPage.buyNow(cartName);
            log.info("Fund: {} | Original price: {} | Selling price: {}", details.name, details.originalPrice, details.sellingPrice);
        }

        profilePage.getCartDetails();
        profilePage.Payment();

        getDriver().get("https://ambtestlab.netlify.app/login");
        loginAsUser(creds[0], creds[1]);

        for (String cartName : cartNames) {
            UserFundBuy.FundDetails details = fundPage.getCartDetails(cartName);
            Assert.assertNotNull(details, "Fund details should not be null for " + cartName);
            Assert.assertFalse(details.originalPrice.isBlank(), "Original price is missing for " + cartName);
            Assert.assertFalse(details.sellingPrice.isBlank(), "Selling price is missing for " + cartName);
            fundPage.addToCart(cartName);
            if (cartName.equals("Ava Taylor")) fundPage.buyNow(cartName);
            log.info("Fund: {} | Original price: {} | Selling price: {}", details.name, details.originalPrice, details.sellingPrice);
        }

        profilePage.getCartDetails();
        profilePage.Payment();

        getDriver().get("https://ambtestlab.netlify.app/login");
        loginAsUser(creds[0], creds[1]);
        profilePage.goToPortfilo();

        Assert.assertTrue(profilePage.getTableRows().size() > 0, "Portfolio table should contain purchased funds");
        profilePage.getTableData();
        log.info("Portfolio table validation completed successfully");
    }
}