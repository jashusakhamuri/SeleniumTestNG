package UI.jashu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebTablesPage {
    private WebDriver driver;
    private WebDriverWait wait ;
    //any importec class like private className  varaible name
    public WebTablesPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Objects fro importrd class like variabrel = new  CLassname;

    }
    private By table =
            By.cssSelector("table.table-striped.table-bordered.table-hover");
    private By tableHeaders = By.cssSelector("table thead th");

    private By tableRows = By.cssSelector("table tbody tr");

    private By tableCells = By.cssSelector("table tbody tr td");
    private By firstNames =
            By.cssSelector("table tbody tr td:nth-child(1)");


    public void goTo() {
        driver.get("https://demoqa.com/webtables");
    }
    public WebElement getTabel (){
        return driver.findElement(table);
    }
    // Get table headers
   public List<WebElement>  getHeaders(){
        return driver.findElements(tableHeaders);
   }

   public void printHeaders(){
        List<WebElement> HeadersNames = getHeaders();
        for(WebElement header : HeadersNames){
            System.out.print(header.getText().trim());
        }
   }
    // Get table rows
    public List<WebElement> getTableRows() {
        return driver.findElements(tableRows);
    }

    public void printTableData(){
        List<WebElement> rows = getTableRows();
        for(WebElement row : rows){
            System.out.println(row.getText().trim());
        }
    }
    public void ColumnsWiseData(){
        List<WebElement> rows = getTableRows();
        for(WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WebElement editButton =
                    row.findElement(By.cssSelector("span[title='Delete']"));

            for (WebElement cell : cells){
                System.out.print(cell.getText().trim() + " | ");
                editButton.click();

            }
            System.out.println("-------------------");

        }
    }

    public void printFirstNames() {

        List<WebElement> names =
                driver.findElements(firstNames);

        for (WebElement name : names) {
            System.out.println(name.getText().trim());
        }
    }
}
