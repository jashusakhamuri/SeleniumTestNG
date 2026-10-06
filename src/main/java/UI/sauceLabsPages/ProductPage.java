package UI.sauceLabsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By countOfItems = By.xpath("//div[@class='inventory_list']//div[@class='inventory_item']");
    private By commonItemName = By.xpath("//div[normalize-space(@class)='inventory_item_name']");
    private By commonDescription = By.xpath("//div[@class='inventory_item_desc']");
    private By pageTitle = By.xpath("//span[@class='title']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }



    public List<String> getDescriptions() {
        List<WebElement> elements = driver.findElements(commonDescription);
        List<String> descriptions = new ArrayList<>();

        for (WebElement element : elements) {
            descriptions.add(element.getText().trim());
        }

        return descriptions;
    }

    public int getItemCount() {
        return driver.findElements(countOfItems).size();
    }

    public String[] getItemNameTexts() {
        List<WebElement> items = driver.findElements(commonItemName);
        String[] itemNames = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getText().trim();
        }
        return itemNames;
    }

    public List<String> getItemNames() {
        List<WebElement> items = driver.findElements(commonItemName);
        List<String> itemNames = new ArrayList<>();

        for (WebElement item : items) {
            itemNames.add(item.getText().trim());
        }

        return itemNames;
    }

    public void addToCart(String itemName) {
       By addToCartButton = By.xpath(
                "//div[@class='inventory_item']" +
                        "[.//div[normalize-space(@class)='inventory_item_name' and text()='" + itemName + "']]" +
                        "//button[text()='Add to cart']"
        );
        driver.findElement(addToCartButton).click();
    }
}