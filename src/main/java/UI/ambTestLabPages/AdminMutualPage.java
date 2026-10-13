
package UI.ambTestLabPages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import UI.ambTestLabUtils.WebElementUtil;
import UI.ambTestLabUtils.model.Fund;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminMutualPage {
    private static final Logger log = LoggerFactory.getLogger(AdminMutualPage.class);

    private final WebDriver driver;
    private final WebElementUtil elementUtils;
    private final WebDriverWait wait;

    // ---------- page-level locators ----------
    private final By MutualFundsTab = By.xpath("//div[@id='sidebar']//a[normalize-space(.)='Mutual Funds']");
    private final By addFundBtn = By.id("addFundBtn");
    private final By toggleFundInputsText = By.id("toggleFundInputsText");
    private final By fundNameInput  = By.xpath("//input[@id='fundNameInput']");
    private final By fundCodeInput = By.xpath("//input[@id='fundCodeInput']");
    private final By fundcategoryInputDropdown = By.xpath("//select[@id='fundCategoryInput']");  // was //Select (case!)
    private final By fundRiskInputDropdown     = By.xpath("//select[@id='fundRiskInput']");      // was //Select (case!)
    private final By fundNavInput  = By.xpath("//input[@id='fundNavInput']");
    private final By fundPriceInput = By.xpath("//input[@id='fundPriceInput']");
    private final By orginalPrice = By.xpath("//input[@id='fundOriginalPriceInput']");
    private final By fundReturn1YInput  = By.xpath("//input[@id='fundReturn1YInput']");
    private final By fundReturn3YInput = By.xpath("//input[@id='fundReturn3YInput']");
    private final By fundReturn5YInput = By.xpath("//input[@id='fundReturn5YInput']");
    private final By fundVendorInput = By.xpath("//input[@id='fundVendorInput']");
    private final By fundLocationInput = By.xpath("//input[@id='fundLocationInput']");
    private final By fundImageInput = By.xpath("//input[@id='fundImageInput']");
    private final By saveFundBtn = By.xpath("//button[normalize-space(.)='Save Fund']");
    private final By allCardNames = By.xpath("//div[@id='mutualFundsGrid']//h5");
    private final By MutualGrid = By.xpath("//div[@id='mutualFundsGrid']");

    public AdminMutualPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new WebElementUtil(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void gotoMutual() {
        elementUtils.click(MutualFundsTab);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allCardNames)); // wait for the grid
    }

    // ---------- pagination ----------
    private By pageNumber(int page) {
        return By.xpath("//nav[@aria-label='Mutual funds pagination']//a[normalize-space()='" + page + "']");
    }
    private By prevOrNext(String text) {
        return By.xpath("//nav[@aria-label='Mutual funds pagination']//a[normalize-space()='" + text + "']");
    }

    /** Click a pagination link, then WAIT until the grid has really refreshed. */
    private void clickPaginationAndWait(By locator, String description) {
        log.info("Navigating using pagination: {}", description);
        elementUtils.scrollIntoView(locator);

        WebElement firstCardBefore = firstCardElement();   // hold a card from the OLD page
        clickWithFallback(locator);

        // wait until that old card is detached = the grid re-rendered
        try {
            if (firstCardBefore != null) {
                wait.until(ExpectedConditions.stalenessOf(firstCardBefore));
            }
        } catch (TimeoutException e) {
            log.warn("Grid did not change after clicking '{}' — already on that page?", description);
        }
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allCardNames));  // new cards ready
    }

    private WebElement firstCardElement() {
        List<WebElement> cards = driver.findElements(allCardNames);
        return cards.isEmpty() ? null : cards.get(0);
    }

    /** Real click first; fall back to a JS click only if something covers the element. */
    private void clickWithFallback(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            log.warn("Click intercepted on {} — falling back to JS click", locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void goToPage(int page) {
        clickPaginationAndWait(pageNumber(page), "page " + page);
    }

    // ---------- reading ----------
    private List<String> getAllCardName() {
        log.info("Reading card names on the current page");
        elementUtils.scrollIntoView(MutualGrid);
        List<String> names = new ArrayList<>();
        for (WebElement element : wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allCardNames))) {
            String name = element.getText().trim();
            if (!name.isEmpty()) names.add(name);
        }
        log.info("Found {} cards on this page: {}", names.size(), names);
        return names;
    }

    /** Read the page you're on, then navigate to the rest — so we never click the page we're already on. */
    public List<String> collectNamesAcrossPages(int... pages) {
        List<String> names = new ArrayList<>(getAllCardName());   // current page first (no click)
        for (int i = 1; i < pages.length; i++) {
            goToPage(pages[i]);
            names.addAll(getAllCardName());
        }
        log.info("Collected {} card names across pages {}", names.size(), Arrays.toString(pages));
        return names;
    }
//stalenessOf means "wait until this element no longer exists in the page."

    /** Click Prev or Next, wait for the refresh, then read. */
    public List<String> collectNamesAcrossPrevOrNext(String text) {
        List<String> names = new ArrayList<>();
        clickPaginationAndWait(prevOrNext(text), text);   // FIXED: this actually clicks now
        names.addAll(getAllCardName());
        log.info("Collected {} card names after clicking '{}'", names.size(), text);
        return names;
    }

    // wrtie single metho to fill form


    public void fillFundForm(Fund fund) {
        elementUtils.click(addFundBtn);
        elementUtils.click(toggleFundInputsText);
        elementUtils.sendString(fundNameInput, fund.name());
        elementUtils.sendString(fundCodeInput, fund.code());
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(fundcategoryInputDropdown)))
                .selectByVisibleText(fund.category());
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(fundRiskInputDropdown)))
                .selectByVisibleText(fund.risk());
        elementUtils.sendString(fundNavInput, fund.nav());
        elementUtils.sendString(fundPriceInput, fund.price());
        elementUtils.sendString(orginalPrice, fund.originalPrice());
        elementUtils.sendString(fundReturn1YInput, fund.return1Y());
        elementUtils.sendString(fundReturn3YInput, fund.return3Y());
        elementUtils.sendString(fundReturn5YInput, fund.return5Y());
        elementUtils.sendString(fundVendorInput, fund.vendor());
        elementUtils.sendString(fundLocationInput, fund.location());
        elementUtils.sendString(fundImageInput, fund.imagePath());
    }

    public void saveFund() {
        elementUtils.click(saveFundBtn);
    }
}
