package UI.sauceLabsUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebElementUtils {

    private WebDriver driver;

    public WebElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void clear(By locator) {
        driver.findElement(locator).clear();
    }

    public boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public boolean isEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }
}