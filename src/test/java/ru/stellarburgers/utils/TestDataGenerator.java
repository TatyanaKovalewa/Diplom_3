package ru.stellarburgers.utils;

import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();

    public static String generateRandomName() {
        return "Тестовый_Пользователь_" + System.currentTimeMillis() % 10000;
    }

    public static String generateRandomEmail() {
        return "test_" + System.currentTimeMillis() + "_" + random.nextInt(1000) + "@test.com";
    }

    public static String generateValidPassword() {
        return "Test" + random.nextInt(1000000);
    }

    public static String generateInvalidPassword() {
        return "12345";
    }
}