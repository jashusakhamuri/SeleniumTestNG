
package UI.ambTestLabPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import UI.ambTestLabUtils.WebElementUtil;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserFundBuy {
    private static final Logger log = LoggerFactory.getLogger(UserFundBuy.class);
    private final WebDriver driver;
    private final WebElementUtil elementUtils;
    private final WebDriverWait wait;
    private final By fundsContainer = By.xpath("//div[@id='fundsContainer']");
    private final By allCardNames = By.xpath("//div[@id='fundsContainer']/descendant::div[@class='doc-pro-name']//a");

    public UserFundBuy(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new WebElementUtil(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By pageNumber(int page) {
        return By.xpath("//nav[@aria-label='Page navigation']//a[normalize-space()='" + page + "']");
    }

    private By paginationControl(String control) {
        return By.xpath("//nav[@aria-label='Page navigation']//a[normalize-space()='" + control + "']");
    }

    private String cardXpath(String cartName) {
        return "//div[normalize-space(.)='" + cartName + "']/parent::div[@class='doc-content flex-grow-1']";
    }

    private By originalPriceOf(String cartName) {
        return By.xpath(cardXpath(cartName) + "//div[@class='rate-card']//del");
    }

    private By sellingPriceOf(String cartName) {
        return By.xpath(cardXpath(cartName) + "//div[@class='rate-selling']");
    }

    private By addButtonOf(String cartName) {
        return By.xpath(cardXpath(cartName) + "//button[normalize-space(.)='Add']");
    }

    private By buyButtonOf(String cartName) {
        return By.xpath(cardXpath(cartName) + "//button[normalize-space(.)='Buy Now']");
    }

    public List<String> getCardNamesOnCurrentPage() {
        log.info("Reading card names on the current page");
        elementUtils.scrollIntoView(fundsContainer);
        List<String> names = new ArrayList<>();
        for (WebElement element : wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allCardNames))) {
            String name = element.getText().trim();
            if (!name.isEmpty()) names.add(name);
        }
        log.info("Found {} cards on this page: {}", names.size(), names);
        return names;
    }

    private void clickPagination(By locator, String description) {
        log.info("Navigating using pagination: {}", description);
        elementUtils.scrollIntoView(locator);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void goToPage(int page) {
        clickPagination(pageNumber(page), "page " + page);
    }

    public void goToNextPage() {
        clickPagination(paginationControl("Next"), "Next");
    }

    public void goToPreviousPage() {
        clickPagination(paginationControl("Prev"), "Prev");
    }

    public void visitPage2AndReturn() {
        goToPage(2);
        goToPreviousPage();
    }

    public List<String> collectNamesAcrossPages(int... pages) {
        List<String> names = new ArrayList<>();
        for (int page : pages) {
            goToPage(page);
            names.addAll(getCardNamesOnCurrentPage());
        }
        driver.get("https://ambtestlab.netlify.app/funds.html");
        log.info("Collected {} card names across pages {}", names.size(), java.util.Arrays.toString(pages));
        return names;
    }

    public List<String> collectNamesAcrossFirstTwoPages() {
        return collectNamesAcrossPages(1, 2);
    }

    public FundDetails getCartDetails(String cartName) {
        log.info("Fetching details for card: {}", cartName);
        elementUtils.scrollIntoView(originalPriceOf(cartName));
        String originalPrice = elementUtils.getText(originalPriceOf(cartName));
        String sellingPrice = elementUtils.getText(sellingPriceOf(cartName));
        return new FundDetails(cartName, originalPrice, sellingPrice);
    }

    public void addToCart(String cartName) {
        log.info("Adding card to cart: {}", cartName);
        elementUtils.clickAfterScroll(addButtonOf(cartName));
    }

    public void buyNow(String cartName) {
        log.info("Buying card immediately: {}", cartName);
        elementUtils.clickAfterScroll(buyButtonOf(cartName));
    }

    public static class FundDetails {
        public String name;
        public String originalPrice;
        public String sellingPrice;

        public FundDetails(String name, String originalPrice, String sellingPrice) {
            this.name = name;
            this.originalPrice = originalPrice;
            this.sellingPrice = sellingPrice;
        }

        @Override
        public String toString() {
            return String.format("FundDetails{Name='%s', Orig='%s', Sell='%s'}", name, originalPrice, sellingPrice);
        }
    }
}
