package utills;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BrowserUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BrowserUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // =========================
    // Window / Tab Handling
    // =========================

    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }


    public Set<String> getAllWindows() {
        return driver.getWindowHandles();
    }


    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }


    public WebDriver switchToNewWindow(Set<String> oldWindows) {

        wait.until(driver ->
                driver.getWindowHandles().size() > oldWindows.size()
        );

        for (String window : driver.getWindowHandles()) {

            if (!oldWindows.contains(window)) {

                driver.switchTo().window(window);
                break;
            }
        }

        return driver;
    }


    public void switchToOriginalWindow(String originalWindow) {

        driver.switchTo().window(originalWindow);
    }


    public void closeCurrentWindow() {

        driver.close();
    }


    // =========================
    // Alert Handling
    // =========================

    public Alert waitForAlert() {

        return wait.until(
                ExpectedConditions.alertIsPresent()
        );
    }


    public String getAlertText() {

        Alert alert = waitForAlert();

        return alert.getText();
    }


    public void acceptAlert() {

        Alert alert = waitForAlert();

        alert.accept();
    }


    public void dismissAlert() {

        Alert alert = waitForAlert();

        alert.dismiss();
    }


    public void enterAlertText(String text) {

        Alert alert = waitForAlert();

        alert.sendKeys(text);
    }


    public void acceptPrompt(String text) {

        Alert alert = waitForAlert();

        alert.sendKeys(text);
        alert.accept();
    }
}