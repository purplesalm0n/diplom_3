package ru.praktikum.utils;

import com.github.javafaker.Faker;

public final class DataGenerator {
    private DataGenerator() {}
    private static final Faker faker = new Faker();

    public static String randomEmail() { return faker.internet().emailAddress(); }
    public static String randomPassword() {
        String p = faker.internet().password(6, 14, true, true, true);
        return p.length() < 6 ? p + "0".repeat(6 - p.length()) : p;
    }
    public static String randomName() { return faker.name().firstName(); }
}
