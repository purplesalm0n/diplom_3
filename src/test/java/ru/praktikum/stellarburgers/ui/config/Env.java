package ru.praktikum.stellarburgers.ui.config;

public final class Env {
    private Env() {}
    private static final String DEFAULT_BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static String baseUrl() {
        String v = System.getProperty("baseUrl");
        return (v == null || v.isBlank()) ? DEFAULT_BASE_URL : v;
    }
}
