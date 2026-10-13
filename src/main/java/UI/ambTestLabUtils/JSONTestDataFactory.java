
        package UI.ambTestLabUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.io.IOException;

public class JSONTestDataFactory {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJson(String fileName, Class<T> modelClass) {
        try (InputStream input = JSONTestDataFactory.class.getClassLoader()
                .getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("JSON file not found: " + fileName);
            }
            return mapper.readValue(input, modelClass);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON: " + fileName, e);
        }
    }
}

/*
Yes bro! ✅ By default, the JSON key names should match the Java model's component names for Jackson to map them automatically.

Example:

JSON

```
{
  "name": "Bluechip Growth Fund",
  "price": "120.50"
}
```

Java record

```
public record Fund(String name, String price) {}
```

Here, `name` maps to `name` and `price` maps to `price`.

What if the names are different?

JSON:

```
{
  "fundName": "Bluechip Growth Fund"
}
```

Java record:

```
public record Fund(
    @JsonProperty("fundName") String name
) {}
```

The `@JsonProperty("fundName")` annotation tells Jackson to map the JSON key `fundName` to the Java component `name`.

Remember: Matching names is the simplest approach, bro. You can use `@JsonProperty` when they differ.
 */