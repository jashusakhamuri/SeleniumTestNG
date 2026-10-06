package UI.jashu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v152.domsnapshot.model.StringIndex;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utills.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AmazonPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private BrowserUtils browserUtils;


    private By searchBox = By.id("twotabsearchtextbox");

    private By suggestions = By.cssSelector(".left-pane-results-container div[role='row']");

    private By productDetails = By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
     private By NavLinks  = By.xpath("//div[@id='nav-subnav']//li");


    public AmazonPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.browserUtils = new BrowserUtils(driver);
    }


    public void goTo() {

        driver.get("https://www.amazon.in/");
    }


    public void searchAndClick(String searchText, String suggestionText) {

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

        search.sendKeys(searchText);


        List<WebElement> suggestionList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestions));

        System.out.println("Suggestion Count = " + suggestionList.size());




        for (WebElement suggestion : suggestionList) {

            String actualText = suggestion.getText().trim();

            System.out.println("Suggestion: " + actualText);


            if (actualText.equals(suggestionText.trim())) {

                suggestion.click();

                System.out.println("-------------------------");
                System.out.println("Clicked suggestion: " + actualText);

                break;
            }
        }
    }


    public WebDriver  getProductDetails(String productText) {

        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productDetails));

        System.out.println("Products found: " + products.size());


        for (WebElement product : products) {

            String actualText = product.getText().trim();

            System.out.println("Product: " + actualText);

            String url1 = driver.getCurrentUrl();
            System.out.println("before url1 "+ url1);


            if (actualText.contains(productText.trim())) {

                WebElement elemt = driver.findElement(By.xpath("//span[text()='"+productText+"']"));

                String OriginalTab = driver.getWindowHandle();

                elemt.click();

                Set<String> mutilpleTab  = driver.getWindowHandles();
                for(String oneTab:mutilpleTab){

                    if (!oneTab.equals(OriginalTab)) {

                        driver.switchTo().window(oneTab);

                        break;
                    }
                }


                System.out.println("-------------------------");
                System.out.println("Clicked product: " + actualText);

                String url2 = driver.getCurrentUrl();
                System.out.println("after url2 "+ url2);


                wait.until(driver -> !driver.getTitle().isEmpty());

                System.out.println("Product Page Title: " + driver.getTitle());
                break;


            }
        }
        return driver;
    }

    public  void getNavLinks(){
        List<WebElement> LinksList = driver.findElements(NavLinks);
        for(WebElement singleLink : LinksList ){
            System.out.println(singleLink.getText().trim());
        }


    }


}