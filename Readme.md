# Selenium + TestNG Project Setup — From Scratch

## 1. Create Project

```text
Project
   ↓
Java
   ↓
Select Java Version
   ↓
Create Project
```

Example:

```text
Java Version → 21
```

---

## 2. Maven Project

Create/select:

```text
Maven
```

Maven creates:

```text
Project
│
├── pom.xml
└── src
```

---

## 3. Configure `pom.xml`

Open:

```text
pom.xml
```

### Dependencies

Add the libraries that **our Java automation code will use**.

```text
Dependencies
│
├── Selenium
└── TestNG
```

### Selenium

Purpose:

```text
Browser Automation
```

Used for:

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
```

### TestNG

Purpose:

```text
Test Execution
```

Used for:

```java
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
```

---

## 4. Maven Plugins

Plugins are tools used by **Maven** during the build/test process.

```text
Plugins
│
├── Compiler Plugin
└── Surefire Plugin
```

### Maven Compiler Plugin

Purpose:

```text
Compile Java code
```

Flow:

```text
.java
  ↓
Compiler Plugin
  ↓
.class
```

### Maven Surefire Plugin

Purpose:

```text
Run tests through Maven
```

Flow:

```text
mvn test
   ↓
Surefire
   ↓
TestNG
   ↓
@Test methods
```

---

# 5. Dependency vs Plugin

Remember:

```text
DEPENDENCIES
    ↓
Used by MY Java code

Selenium
TestNG
```

```text
PLUGINS
    ↓
Used by MAVEN

Compiler
Surefire
```

---

# 6. Imports

After Maven downloads the dependencies, we can import the classes we need.

### Selenium imports

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
```

Example:

```java
WebDriver driver = new ChromeDriver();
```

---

### TestNG imports

```java
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
```

Example:

```java
@Test
public void loginTest() {
}
```

---

# 7. Complete Setup Flow

Remember this order:

```text
Project
   ↓
Java
   ↓
Select Java Version
   ↓
Maven
   ↓
pom.xml
   ↓
Dependencies
   ├── Selenium
   └── TestNG
   ↓
Plugins
   ├── Maven Compiler
   └── Maven Surefire
   ↓
Create Java Test Class
   ↓
Import Selenium / TestNG classes
   ↓
Write Test
   ↓
Run Test
```

---

# 8. Where Do We Find Them?

### Dependencies

Search:

```text
Maven Central
```

or:

```text
MvnRepository
```

Search for:

```text
selenium-java
testng
```

### Plugins

Search:

```text
Maven Compiler Plugin
Maven Surefire Plugin
```

Prefer the official Maven/Apache documentation when learning what a plugin does.

---

# 9. Final Mental Model

```text
                    PROJECT
                       │
                       ↓
                      JAVA
                       │
                       ↓
                     MAVEN
                       │
                       ↓
                    pom.xml
                       │
            ┌──────────┴──────────┐
            ↓                     ↓
       DEPENDENCIES             PLUGINS
            │                     │
       ┌────┴────┐           ┌────┴────┐
       ↓         ↓           ↓         ↓
   Selenium    TestNG    Compiler   Surefire
       │         │           │         │
       ↓         ↓           ↓         ↓
   Browser     Tests      Compile    Run Tests
  Automation
```

## Quick Revision

```text
Project
   ↓
Java Version
   ↓
Maven
   ↓
pom.xml
   ↓
Dependencies
   ├── Selenium
   └── TestNG
   ↓
Plugins
   ├── Compiler
   └── Surefire
   ↓
Imports
   ├── Selenium imports
   └── TestNG imports
   ↓
Write Test
   ↓
Run Test
```
