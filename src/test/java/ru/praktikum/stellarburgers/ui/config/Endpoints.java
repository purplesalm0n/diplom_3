package ru.praktikum.stellarburgers.ui.config;

public final class Endpoints {
    private Endpoints() {}
    public static final String INGREDIENTS    = "/api/ingredients";
    public static final String REGISTER       = "/api/auth/register";
    public static final String LOGIN          = "/api/auth/login";
    public static final String LOGOUT         = "/api/auth/logout";
    public static final String TOKEN          = "/api/auth/token";
    public static final String USER           = "/api/auth/user";
    public static final String ORDERS         = "/api/orders";
    public static final String ORDERS_PUBLIC  = "/api/orders/all";
}
