package UI.ambTestLabUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class WebElementUtil {
    private WebDriver driver;
    // driver initliazation
    private WebDriverWait wait;
    //constructor created
    public WebElementUtil(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    public void click(By locator){
       wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public void sendString(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
    public String getInputValue(By locator) {
        return wait.until(driver -> {
            String value = driver.findElement(locator).getDomProperty("value");
            return value != null && !value.isBlank() ? value : null;
        });
    }

//    public String getToastMessage(By locator) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
//                .getText().trim();
//    }
    public String getToastMessage(By locator) {
        return wait.until(driver -> {
            String text = driver.findElement(locator).getText().trim();
            return text.isEmpty() ? null : text;
        });
    }

    public void scrollIntoView(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
    public void clickAfterScroll(By locator) {
        scrollIntoView(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }


    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }


}
