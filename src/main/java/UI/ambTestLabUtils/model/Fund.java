package UI.ambTestLabUtils.model;

public record Fund(
        String name, String code, String category, String risk,
        String nav, String price, String originalPrice,
        String return1Y, String return3Y, String return5Y,
        String vendor, String location, String imagePath
) {}
//Here's the whole reason:
//
//Your form has 13 fields (name, code, category, price…).
//The JSON file has those 13 values.
//Jackson reads the file and must put those values somewhere in Java.
//But Java can't hand you "13 values" as a thing — a method returns one thing.
//file (JSON)  →  reader  →  model (Fund)  →  page object  →  test
//  the data      loads it    the typed shape    fills the UI    asserts`



/*

Bro 😄 good question! In Java, `record` is a special type introduced in Java 16 to make classes that primarily store data simpler. Since you're using Java 21, you can use it directly.

## 1. Why doesn't `Fund` have a method body?

Your code:

```
public record Fund(
    String name, String code, String category, String risk,
    String nav, String price, String originalPrice,
    String return1Y, String return3Y, String return5Y,
    String vendor, String location, String imagePath
) {}
```

This is a complete record declaration. The compiler automatically generates the constructor, accessor methods, and other methods for you.

Think of it like this:

Your record

```
public record Fund(String name, String code) {}
```

Conceptually similar to a regular class

```
public final class Fund {
    private final String name;
    private final String code;

    public Fund(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }
}
```

The compiler generates the record's standard implementation for you. The regular class above is a simplified illustration.

## 2. What methods does Java generate?

For your `Fund` record, Java provides:

| Generated member | Purpose                                 |
| ---------------- | --------------------------------------- |
| `Fund(...)`      | Constructor to initialize all 13 fields |
| `name()`         | Returns the fund name                   |
| `code()`         | Returns the fund code                   |
| `category()`     | Returns the category                    |
| `equals()`       | Compares record values                  |
| `hashCode()`     | Generates a hash code                   |
| `toString()`     | Provides a readable representation      |

You don't need to write these methods yourself.

## 3. How are you using it in your project?

When Jackson reads your JSON:

```
Fund fund = JSONTestDataFactory.readJson(
    "testdata/fund.json", Fund.class
);
```

Jackson creates a `Fund` object and populates its components from the JSON fields.

You can then access the values:

```
System.out.println(fund.name());
System.out.println(fund.price());
System.out.println(fund.vendor());
```

Notice that you use `fund.name()`, not `fund.getName()`.

- Records use accessor methods like `name()`.
- Traditional Java classes commonly use getters like `getName()`.

## 4. Can a record have a method body?

Yes, bro! You can add your own methods whenever you need extra behavior.

```
public record Fund(String name, String price) {
    public void displayDetails() {
        System.out.println(name + " - " + price);
    }
}
```

Here, `displayDetails()` has a body because you explicitly defined it. Java generates the standard record members automatically.

Remember: Use a `record` when you mainly need to represent data, like your JSON test data. Use a regular class when you need mutable fields or more complex object behavior.
 */