package UI.ambTestLabUtils;

import com.github.javafaker.Faker;

public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static String getFirstName() { return faker.name().firstName(); }
    public static String getMiddleName() { return faker.name().firstName(); }
    public static String getLastName() { return faker.name().lastName(); }
    public static String getEmail() { return "user_" + java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 12) + "@example.com"; }
    public static String getPasswordF() { return "Test@" + faker.number().digits(6); }
    public static String getMobileNo() { return "9" + faker.number().digits(9); }
    public static String getAddress() { return faker.address().fullAddress(); }
    public static String getLocation() { return faker.address().city(); }

}