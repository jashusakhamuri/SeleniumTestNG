     package UI.ambTestLabPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import UI.ambTestLabUtils.WebElementUtil;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserPortfiloPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(UserPortfiloPage.class);
    private  WebElementUtil elementUtils;

    private final By summaryCount = By.id("summaryCount");
    private final By summaryTotal = By.id("summaryTotal");
    private final By gotoporfilo = By.xpath("//div[@id='navbarNav']//span[normalize-space(.) = 'My Portfolio']");
    private final By cartItemsNames = By.xpath("//div[@id='portfolioGrid']//div[@class='doc-pro-name']//h5");
    private final By purchaseInvested = By.id("purchaseInvested");
    private final By purchaseSavings = By.id("purchaseSavings");
    private final By table = By.xpath("//table[@class='table table-sm align-middle mb-0']");
    private final By tableHeaders = By.xpath("//table[@class='table table-sm align-middle mb-0']//thead//tr//th");
    private final By tableRows = By.xpath("//table[@class='table table-sm align-middle mb-0']//tbody//tr");
    private final By ProccedToBuy = By.xpath("//button[normalize-space(.)='Proceed to Buy']");
    private final By completePayment  = By.xpath("//button[normalize-space(.)='Complete Secure Payment']");



    public UserPortfiloPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.elementUtils = new WebElementUtil(driver);
    }

    public WebElement getTable() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(table));
    }

    public List<WebElement> getTableRows() {
        getTable();
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
    }

    public List<WebElement> getHeaders() {
        getTable();
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableHeaders));
    }

    public void getTableData() {
        List<WebElement> headers = getHeaders();
        List<WebElement> rows = getTableRows();

        log.info("Table headers: {}", headers.stream().map(WebElement::getText).toList());

        if (rows.isEmpty()) {
            log.info("No table rows found.");
            return;
        }

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            log.info("{}", cells.stream().map(WebElement::getText).toList());
        }

    }

    public List<String> getCartDetails(){
        log.info(elementUtils.getText(summaryCount));
        log.info(elementUtils.getText(summaryTotal));
//        log.info(elementUtils.getText(purchaseInvested));

        List<String> names = new ArrayList<>();

        for (WebElement element : wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItemsNames))) {
            String name = element.getText().trim();
            if (!name.isEmpty()) names.add(name);
        }
        log.info("Found {} cards on this page: {}", names.size(), names);
        return names;
    }

    public void Payment(){
        elementUtils.clickAfterScroll(ProccedToBuy);
        elementUtils.click(completePayment);
    }
 public void goToPortfilo(){
        elementUtils.click(gotoporfilo);
 }

    }
