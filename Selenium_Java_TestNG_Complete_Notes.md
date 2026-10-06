# Selenium Java + TestNG — Complete Automation Notes

## 1. Selenium Locator Strategies

Selenium provides several locator strategies through the `By` class.

| Locator | Example | What it finds |
|---|---|---|
| `By.id()` | `By.id("firstName")` | `id` attribute |
| `By.name()` | `By.name("email")` | `name` attribute |
| `By.className()` | `By.className("form-control")` | CSS class |
| `By.tagName()` | `By.tagName("input")` | HTML tag |
| `By.linkText()` | `By.linkText("Login")` | Exact link text |
| `By.partialLinkText()` | `By.partialLinkText("Log")` | Partial link text |
| `By.cssSelector()` | `By.cssSelector("#firstName")` | CSS selector |
| `By.xpath()` | `By.xpath("//input[@id='firstName']")` | XPath |

### Common examples

```java
driver.findElement(By.id("username"));
driver.findElement(By.name("email"));
driver.findElement(By.className("form-control"));
driver.findElement(By.tagName("input"));
driver.findElement(By.linkText("Login"));
driver.findElement(By.partialLinkText("Log"));
driver.findElement(By.cssSelector("#username"));
driver.findElement(By.xpath("//input[@id='username']"));
```

---

# 2. WebElement

`WebElement` represents an element on the web page.

Example:

```java
WebElement username = driver.findElement(By.id("username"));
```

The most commonly used methods are:

```text
sendKeys()
click()
clear()

getText()
getAttribute()

isDisplayed()
isEnabled()
isSelected()
```

---

# 3. WebElement Actions

## 3.1 sendKeys()

Used to type text or send keyboard input.

Commonly used for:

- Text boxes
- Password fields
- Search boxes
- Input fields
- File upload

Example:

```java
WebElement username = driver.findElement(By.id("username"));
username.sendKeys("Jaswanth");
```

---

## 3.2 click()

Used to click an element.

Common examples:

- Buttons
- Links
- Checkboxes
- Radio buttons
- Dropdown triggers

Example:

```java
WebElement submit = driver.findElement(By.id("submit"));
submit.click();
```

### Checkbox / Radio Example

```java
WebElement male = driver.findElement(By.id("gender-radio-1"));
male.click();
```

---

## 3.3 clear()

Used to remove existing text from an input field.

Example:

```java
WebElement username = driver.findElement(By.id("username"));
username.clear();
username.sendKeys("Jaswanth");
```

Flow:

```text
Existing value
     ↓
   clear()
     ↓
   Empty
     ↓
sendKeys("Jaswanth")
     ↓
Jaswanth
```

---

# 4. Reading Data from WebElements

There are two important methods:

```text
getText()
getAttribute()
```

The important difference is:

```text
getText()       → visible text
getAttribute()  → attribute/property value
```

---

# 5. getText()

`getText()` retrieves visible text displayed inside an element.

Example:

```html
<h1>Student Registration Form</h1>
```

Java:

```java
WebElement heading = driver.findElement(By.tagName("h1"));
String text = heading.getText();
System.out.println(text);
```

Output:

```text
Student Registration Form
```

### Button example

HTML:

```html
<button>Submit</button>
```

Java:

```java
String text = submitButton.getText();
```

Result:

```text
Submit
```

### Common elements for getText()

```text
Headings
Labels
Messages
Links
Buttons
Divs
Spans
```

---

# 6. getAttribute()

`getAttribute()` retrieves an HTML attribute/property value.

Example:

```html
<input id="firstName"
       placeholder="First Name"
       value="Jaswanth">
```

Java:

```java
firstName.getAttribute("id");
firstName.getAttribute("placeholder");
firstName.getAttribute("value");
```

Results:

```text
id          → firstName
placeholder → First Name
value       → Jaswanth
```

---

# 7. getText() vs getAttribute("value")

This is very important.

For normal visible text:

```java
message.getText();
```

For an input field:

```html
<input id="username" value="John">
```

Use:

```java
username.getAttribute("value");
```

Do not normally use:

```java
username.getText();
```

### Easy rule

```text
Visible text inside element
        ↓
getText()

Input field value
        ↓
getAttribute("value")
```

---

# 8. WebElement State Methods

## 8.1 isDisplayed()

Checks whether the element is visible.

```java
element.isDisplayed();
```

Think:

```text
Can I see it?
```

Example:

```java
Assert.assertTrue(username.isDisplayed());
```

---

## 8.2 isEnabled()

Checks whether the element is enabled.

```java
element.isEnabled();
```

Think:

```text
Can I interact with it?
```

Example:

```java
Assert.assertTrue(submitButton.isEnabled());
```

For a disabled button:

```java
Assert.assertFalse(submitButton.isEnabled());
```

---

## 8.3 isSelected()

Checks whether an element is selected.

Commonly used for:

```text
Checkbox
Radio button
Select option
```

Example:

```java
WebElement male = driver.findElement(By.id("gender-radio-1"));
Assert.assertTrue(male.isSelected());
```

---

# 9. Easy WebElement Memory

## ACTION

```text
sendKeys() → Type
click()    → Click
clear()    → Remove text
```

## READ / VERIFY

```text
getText()       → Visible text
getAttribute()  → Attribute/property value

isDisplayed()   → Visible?
isEnabled()     → Enabled?
isSelected()    → Selected?
```

### Easy distinction

```text
isDisplayed() → "Can I see it?"

isEnabled() → "Is it enabled?"

isSelected() → "Is it selected?"
```

These methods only check state. They do not force an interaction.

---

# 10. getAttribute("value") — Input Fields

Use:

```java
getAttribute("value")
```

for input fields such as:

```text
Text box
Password field
Search box
Input fields
```

Example:

```java
WebElement username = driver.findElement(By.id("username"));
System.out.println(username.getAttribute("value"));
```

HTML:

```html
<input id="username" value="John">
```

Output:

```text
John
```

---

# 11. getText() — Visible Page Text

Use:

```java
getText()
```

for text displayed on the page.

Commonly used for:

```text
Links
Messages
Buttons
Headings
Labels
Divs
Spans
```

Example:

```java
WebElement message = driver.findElement(By.id("message"));
System.out.println(message.getText());
```

HTML:

```html
<div id="message">Welcome John</div>
```

Output:

```text
Welcome John
```

---

# 12. Multiple Windows / Tabs

Selenium provides:

```java
driver.getWindowHandle();
driver.getWindowHandles();
driver.switchTo().window(handle);
```

---

## 12.1 getWindowHandle()

Returns the handle of the current browser window/tab.

```java
String originalWindow = driver.getWindowHandle();
```

Think:

```text
Current tab
    ↓
getWindowHandle()
    ↓
Unique ID of current tab
```

---

## 12.2 getWindowHandles()

Returns all currently available window/tab handles.

```java
Set<String> allWindows = driver.getWindowHandles();
```

Example:

```java
String originalWindow = driver.getWindowHandle();

element.click();

Set<String> allWindows = driver.getWindowHandles();
```

---

## 12.3 switchTo().window()

Used to switch Selenium's focus to another window/tab.

```java
driver.switchTo().window(originalWindow);
```

Example:

```java
String originalWindow = driver.getWindowHandle();

element.click();

Set<String> allWindows = driver.getWindowHandles();

for (String window : allWindows) {
    if (!window.equals(originalWindow)) {
        driver.switchTo().window(window);
        break;
    }
}
```

### Important

Opening a new tab does not automatically mean Selenium switches to it.

You must explicitly switch:

```java
driver.switchTo().window(handle);
```

---

# 13. Browser Windows — Basic Flow

```text
Save current window
        ↓
Click element that opens new window
        ↓
Get all window handles
        ↓
Find new window
        ↓
Switch to new window
        ↓
Perform actions
        ↓
Switch back if required
```

Example:

```java
String originalWindow = driver.getWindowHandle();

element.click();

Set<String> allWindows = driver.getWindowHandles();

for (String window : allWindows) {
    if (!window.equals(originalWindow)) {
        driver.switchTo().window(window);
        break;
    }
}

driver.switchTo().window(originalWindow);
```

---

# 14. Alerts

Selenium handles JavaScript alerts using:

```java
driver.switchTo().alert();
```

Example:

```java
Alert alert = driver.switchTo().alert();
```

---

## 14.1 getText()

Gets alert message.

```java
System.out.println(alert.getText());
```

---

## 14.2 sendKeys()

Used for prompt alerts that accept input.

```java
alert.sendKeys("Jaswanth");
```

---

## 14.3 accept()

Clicks OK.

```java
alert.accept();
```

---

## 14.4 dismiss()

Clicks Cancel.

```java
alert.dismiss();
```

---

# 15. Alert Complete Example

```java
Alert alert = driver.switchTo().alert();
System.out.println(alert.getText());
alert.sendKeys("Jaswanth");
alert.accept();
```

For confirmation:

```java
Alert alert = driver.switchTo().alert();
alert.dismiss();
```

---

# 16. DataProvider

TestNG `@DataProvider` is used to execute the same test with multiple sets of data.

Basic structure:

```java
@DataProvider(name = "LoginData")
public Object[][] loginData() {
    return new Object[][]{
        {"user1", "password1"},
        {"user2", "password2"},
        {"user3", "password3"}
    };
}
```

Use it with:

```java
@Test(dataProvider = "LoginData")
public void loginTest(String username, String password) {
    // test
}
```

### Execution

The test runs once for each row:

```text
Row 1 → user1 / password1
Row 2 → user2 / password2
Row 3 → user3 / password3
```

---

# 17. Waits

Selenium commonly uses:

```text
Thread.sleep()
Implicit Wait
Explicit Wait
Fluent Wait
```

For automation frameworks, **Explicit Wait is especially important**.

---

# 18. Thread.sleep()

Example:

```java
Thread.sleep(3000);
```

Meaning:

```text
Always wait 3 seconds
```

Problem:

The application might be ready after 1 second, but Selenium still waits 3 seconds.

Or the application might take 5 seconds, and 3 seconds is not enough.

Therefore, avoid using `Thread.sleep()` as the primary synchronization strategy.

---

# 19. Implicit Wait

Example:

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

Implicit wait applies to element lookup.

Think:

```text
Selenium tries to find element
        ↓
Element not immediately available
        ↓
Selenium waits up to configured time
```

### Easy memory

```text
Implicit Wait
    ↓
Wait during element lookup
```

---

# 20. Explicit Wait

Explicit wait waits for a **specific condition**.

Create:

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```

Then:

```java
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
```

### Easy memory

```text
Explicit Wait
    ↓
Wait for a specific condition
```

---

# 21. Common Explicit Wait Conditions

## visibilityOfElementLocated()

Waits until the element is present and visible.

```java
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
```

---

## elementToBeClickable()

Waits until an element is clickable.

```java
wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
```

---

## presenceOfElementLocated()

Waits until the element exists in the DOM.

```java
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
```

---

## textToBePresentInElement()

Waits until specific text appears inside an element.

```java
wait.until(ExpectedConditions.textToBePresentInElement(element, "Welcome"));
```

---

## alertIsPresent()

Waits until an alert appears.

```java
wait.until(ExpectedConditions.alertIsPresent());
```

Then:

```java
Alert alert = driver.switchTo().alert();
```

---

## numberOfWindowsTo()

Waits until a specific number of windows/tabs exist.

```java
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
```

---

# 22. Implicit vs Explicit Wait

| Wait | Purpose |
|---|---|
| `Thread.sleep()` | Fixed delay |
| Implicit Wait | Element lookup |
| Explicit Wait | Specific condition |
| Fluent Wait | Explicit wait with custom polling/ignored exceptions |

### Easy memory

```text
Thread.sleep()
    ↓
Just wait

Implicit Wait
    ↓
Wait during element lookup

Explicit Wait
    ↓
Wait for a specific condition
```

---

# 23. WebElement vs Actions

Easy memory:

```text
WebElement → Simple interaction

Actions → Complex mouse + keyboard interaction
```

### WebElement

Use for:

```java
element.click();
element.sendKeys("text");
element.clear();
```

### Actions

Used for advanced interactions such as:

```text
Mouse hover
Right click
Double click
Drag and drop
Keyboard combinations
```

Example:

```java
Actions actions = new Actions(driver);
actions.moveToElement(element).perform();
```

Right click:

```java
actions.contextClick(element).perform();
```

Double click:

```java
actions.doubleClick(element).perform();
```

---

# 24. Logging

For logging, the framework can use SLF4J + Logback.

Maven dependency:

```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.5.20</version>
</dependency>
```

Example:

```java
private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
```

Then:

```java
log.info("Starting login test");
log.warn("Warning message");
log.error("Test failed");
```

Logging is useful for:

```text
Test execution information
Debugging
Failures
Browser actions
CI/CD execution
```

---

# 25. Configuration Management

Instead of hardcoding values inside Java classes, store configuration in a properties file.

Example:

```text
src/test/resources/config/qa.properties
```

Contents:

```properties
base.url=https://www.saucedemo.com/
username=standard_user
password=secret_sauce
```

Then create a configuration reader.

Example:

```java
public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config/qa.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
```

Usage:

```java
ConfigReader.get("base.url");
ConfigReader.get("username");
ConfigReader.get("password");
```

### Why configuration files?

Instead of:

```java
driver.get("https://www.saucedemo.com/");
```

use:

```java
driver.get(ConfigReader.get("base.url"));
```

This makes environment changes easier.

---

# 26. Maven Project Structure

A clean Selenium + TestNG project can be structured as:

```text
TestNg/
│
├── pom.xml
├── README.md
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── UI/
│   │           ├── sauceLabsPages/
│   │           │   ├── LoginPage.java
│   │           │   ├── ProductPage.java
│   │           │   ├── CartPage.java
│   │           │   └── CheckoutPage.java
│   │           │
│   │           └── sauceLabsUtils/
│   │               ├── DriverFactory.java
│   │               ├── ConfigReader.java
│   │               └── WebElementUtils.java
│   │
│   └── test/
│       ├── java/
│       │   └── UI/
│       │       └── SauceLabsTests/
│       │           ├── BaseTest.java
│       │           ├── LoginTest.java
│       │           ├── ProductTest.java
│       │           ├── CartTest.java
│       │           │
│       │           └── listeners/
│       │               └── TestListener.java
│       │
│       └── resources/
│           ├── config/
│           │   └── qa.properties
│           │
│           ├── testng/
│           │   └── sauceLabs.xml
│           │
│           └── logback.xml
│
├── screenshots/
│
└── target/
```

---

# 27. Page Object Model

The Page Object Model separates:

```text
Page locators + page actions
```

from:

```text
Test scenarios
```

Example:

```java
public class LoginPage {
    private WebDriver driver;

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.id("login");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
    }
}
```

Then the test stays focused on the scenario:

```java
@Test
public void verifyLogin() {
    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
}
```

---

# 28. DriverFactory

The DriverFactory is responsible for browser creation.

Our framework uses:

```java
private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
```

This allows parallel tests to maintain separate browser instances.

Conceptually:

```text
Thread 1 → ChromeDriver 1
Thread 2 → ChromeDriver 2
Thread 3 → ChromeDriver 3
```

Store the driver:

```java
driver.set(webDriver);
```

Retrieve it:

```java
driver.get();
```

Remove it after execution:

```java
driver.remove();
```

### Easy memory

```text
.set() → Store driver for current thread

.get() → Retrieve driver for current thread

.remove() → Remove driver from current thread
```

---

# 29. Browser Selection

The framework can use Chrome as the default browser and allow another browser to be supplied from the terminal.

Example:

```text
mvn test
```

Runs:

```text
Chrome
```

Firefox:

```text
mvn test -Dbrowser=firefox
```

Edge:

```text
mvn test -Dbrowser=edge
```

The browser property can be read using:

```java
String browser = System.getProperty("browser", "chrome");
```

Meaning:

```text
browser not supplied
        ↓
chrome

browser=firefox
        ↓
firefox

browser=edge
        ↓
edge
```

---

# 30. BaseTest

`BaseTest` provides common setup and teardown for tests.

Conceptually:

```text
@BeforeMethod
    ↓
Create driver
    ↓
Get current thread driver
    ↓
Create LoginPage
    ↓
Open application
    ↓
Login
    ↓
Test
    ↓
@AfterMethod
    ↓
Quit driver
```

Example:

```java
@BeforeMethod
public void setup() {
    DriverFactory.createDriver();
    driver = DriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    loginPage.goTo();
    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
}
```

Teardown:

```java
@AfterMethod
public void tearDown() {
    DriverFactory.quitDriver();
}
```

---

# 31. Inheritance and Driver Usage

Test classes extend `BaseTest`.

Example:

```java
public class ProductTest extends BaseTest {
    
    @Test
    public void verifyProducts() {
        ProductPage productPage = new ProductPage(driver);
    }
}
```

Because:

```java
public class ProductTest extends BaseTest
```

`ProductTest` inherits:

```java
protected WebDriver driver;
```

Flow:

```text
DriverFactory
      ↓
ChromeDriver / FirefoxDriver / EdgeDriver
      ↓
BaseTest.driver
      ↓
ProductTest inherits driver
      ↓
ProductPage receives driver
```

The same WebDriver object is passed to the Page Object.

---

# 32. TestNG Parallel Execution

TestNG can execute test classes in parallel.

Example:

```xml
<suite name="SauceLabs Suite" parallel="classes" thread-count="3">
```

Conceptually:

```text
Thread 1 → LoginTest
Thread 2 → ProductTest
Thread 3 → CartTest
```

With ThreadLocal:

```text
Thread 1 → Driver 1
Thread 2 → Driver 2
Thread 3 → Driver 3
```

This prevents browser instances from interfering with each other.

---

# 33. TestNG XML

Example:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<suite name="SauceLabs Suite" parallel="classes" thread-count="3">
    <test name="SauceLabs Tests">
        <classes>
            <class name="UI.SauceLabsTests.LoginTest"/>
            <class name="UI.SauceLabsTests.ProductTest"/>
            <class name="UI.SauceLabsTests.CartTest"/>
        </classes>
    </test>
</suite>
```

This controls:

```text
Which tests run
Parallel execution
Number of threads
```

---

# 34. Maven

Maven manages:

```text
Dependencies
Build
Compilation
Test execution
Plugins
Project lifecycle
```

Common commands:

```bash
mvn clean
mvn compile
mvn test
mvn clean test
```

Browser selection:

```bash
mvn clean test
```

Default:

```text
Chrome
```

Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

Edge:

```bash
mvn clean test -Dbrowser=edge
```

---

# 35. Maven `pom.xml`

The `pom.xml` contains project configuration.

Basic sections:

```text
Project information
    ↓
Properties
    ↓
Dependencies
    ↓
Build plugins
```

Example dependencies:

```xml
<dependencies>

    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.49.0</version>
    </dependency>

    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.12.0</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.5.20</version>
    </dependency>

</dependencies>
```

---

# 36. Maven Dependencies vs Plugins

## Dependency

A dependency provides a library your Java code uses.

Examples:

```text
Selenium
TestNG
Logback
```

## Plugin

A Maven plugin performs build-related tasks.

Examples:

```text
maven-compiler-plugin
maven-surefire-plugin
```

Easy memory:

```text
Dependency → Used by your Java code

Plugin → Used by Maven
```

---

# 37. Maven Compiler Plugin

The compiler plugin compiles Java source code.

Example:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.14.1</version>
</plugin>
```

The project uses Java 21:

```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>
```

---

# 38. Maven Surefire Plugin

Surefire is responsible for running tests during Maven's `test` phase.

Example:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.5.3</version>
</plugin>
```

It can also execute the TestNG suite XML:

```xml
<configuration>
    <suiteXmlFiles>
        <suiteXmlFile>${project.basedir}/src/test/resources/testng/sauceLabs.xml</suiteXmlFile>
    </suiteXmlFiles>
</configuration>
```

---

# 39. Logging vs System.out.println()

Instead of:

```java
System.out.println("Starting test");
```

Use:

```java
log.info("Starting test");
```

Logging provides better control over:

```text
INFO
WARN
ERROR
DEBUG
```

It is especially useful in:

```text
CI/CD
Parallel execution
Failure debugging
Automation frameworks
```

---

# 40. Test Listener

A TestNG listener can react to test lifecycle events.

Common events:

```text
onTestStart()
onTestSuccess()
onTestFailure()
onTestSkipped()
```

A listener can be used for:

```text
Logging
Screenshots
Reports
Failure handling
Test execution information
```

Example:

```java
@Override
public void onTestFailure(ITestResult result) {
    log.error("Test failed: {}", result.getName(), result.getThrowable());
}
```

A failure listener can also capture a screenshot:

```java
File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
```

---

# 41. Framework Architecture

Our current framework follows this general architecture:

```text
                    pom.xml
                       │
              ┌────────┴────────┐
              │                 │
        Dependencies         Plugins
              │                 │
       Selenium/TestNG     Compiler/Surefire
              │
              ▼
         TestNG XML
              │
              ▼
          BaseTest
              │
       ┌──────┴──────┐
       │             │
ConfigReader   DriverFactory
                     │
               ThreadLocal
                     │
                     ▼
                WebDriver
                     │
                     ▼
               Page Objects
                     │
                     ▼
                  Tests
                     │
                     ▼
                 Listener
                     │
              Logs/Screenshots
```

---

# 42. Recommended Framework Responsibilities

| Component | Responsibility |
|---|---|
| `LoginPage` | Login page locators/actions |
| `ProductPage` | Product page locators/actions |
| `CartPage` | Cart page locators/actions |
| `DriverFactory` | Create/manage browser |
| `ConfigReader` | Read configuration |
| `WebElementUtils` | Common element operations |
| `BaseTest` | Setup/teardown |
| `TestListener` | Test lifecycle handling |
| `*.properties` | Environment configuration |
| `*.xml` | TestNG execution |
| `logback.xml` | Logging configuration |
| Test classes | Test scenarios/assertions |

---

# 43. Complete Industry-Oriented Project Structure

```text
TestNg/
│
├── pom.xml
├── README.md
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── UI/
│   │           ├── sauceLabsPages/
│   │           │   ├── LoginPage.java
│   │           │   ├── ProductPage.java
│   │           │   ├── CartPage.java
│   │           │   └── CheckoutPage.java
│   │           │
│   │           └── sauceLabsUtils/
│   │               ├── DriverFactory.java
│   │               ├── ConfigReader.java
│   │               └── WebElementUtils.java
│   │
│   └── test/
│       ├── java/
│       │   └── UI/
│       │       └── SauceLabsTests/
│       │           ├── BaseTest.java
│       │           ├── LoginTest.java
│       │           ├── ProductTest.java
│       │           ├── CartTest.java
│       │           └── listeners/
│       │               └── TestListener.java
│       │
│       └── resources/
│           ├── config/
│           │   └── qa.properties
│           │
│           ├── testng/
│           │   └── sauceLabs.xml
│           │
│           └── logback.xml
│
├── screenshots/
│
└── target/
```

---

# 44. Quick Interview Revision

## Locators

```text
id
name
className
tagName
linkText
partialLinkText
cssSelector
xpath
```

## WebElement Actions

```text
sendKeys()
click()
clear()
```

## WebElement Reading

```text
getText()
getAttribute()
```

## WebElement State

```text
isDisplayed()
isEnabled()
isSelected()
```

## Windows

```text
getWindowHandle()
getWindowHandles()
switchTo().window()
```

## Alerts

```text
switchTo().alert()
getText()
sendKeys()
accept()
dismiss()
```

## Waits

```text
Thread.sleep()
Implicit Wait
Explicit Wait
Fluent Wait
```

## Explicit Wait Conditions

```text
visibilityOfElementLocated()
elementToBeClickable()
presenceOfElementLocated()
textToBePresentInElement()
alertIsPresent()
numberOfWindowsToBe()
```

## TestNG

```text
@Test
@BeforeMethod
@AfterMethod
@DataProvider
```

## Framework

```text
POM
BaseTest
DriverFactory
ThreadLocal
ConfigReader
WebElementUtils
Listener
Logging
TestNG XML
Maven
Parallel execution
Cross-browser execution
```

---

# 45. Most Important Things to Remember

```text
getText()
    ↓
Visible text

getAttribute("value")
    ↓
Input field value

isDisplayed()
    ↓
Visible?

isEnabled()
    ↓
Enabled?

isSelected()
    ↓
Selected?
```

```text
WebElement
    ↓
Simple interaction

Actions
    ↓
Advanced mouse + keyboard interaction
```

```text
Implicit Wait
    ↓
Element lookup

Explicit Wait
    ↓
Specific condition
```

```text
getWindowHandle()
    ↓
Current window

getWindowHandles()
    ↓
All windows

switchTo().window()
    ↓
Switch window
```

```text
Alert
    ↓
switchTo().alert()

accept()
    ↓
OK

dismiss()
    ↓
Cancel
```

```text
ThreadLocal<WebDriver>
    ↓
One WebDriver per thread
    ↓
Parallel execution
```

```text
ConfigReader
    ↓
External configuration

DriverFactory
    ↓
Browser management

BaseTest
    ↓
Setup / teardown

Page Objects
    ↓
UI actions

Test Classes
    ↓
Scenarios + assertions

Listener
    ↓
Screenshots / logging / reporting
```

---

# 46. Final Framework Goal

The goal is not just to write Selenium scripts.

The goal is to build a framework where:

```text
Tests
  ↓
are clean and readable

Page Objects
  ↓
contain UI behavior

Utilities
  ↓
contain reusable functionality

Configuration
  ↓
contains environment-specific values

DriverFactory
  ↓
controls browser creation

ThreadLocal
  ↓
supports parallel execution

TestNG
  ↓
controls test execution

Listeners
  ↓
handle failures/screenshots

Logging
  ↓
helps debugging

Maven
  ↓
builds and executes the framework

CI/CD
  ↓
runs the framework automatically
```

This gives us a maintainable Selenium + Java + TestNG automation framework instead of a collection of individual Selenium scripts.
