package ru.praktikum.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiClient {
    private final String base;
    public ApiClient(String baseUrl) { this.base = baseUrl; }

    @Step("POST /api/auth/register")
    public ValidatableResponse register(UserDto u) {
        return given().baseUri(base).contentType(ContentType.JSON)
                .body(u).when().post("/api/auth/register").then();
    }

    @Step("POST /api/auth/login")
    public ValidatableResponse login(String email, String password) {
        return given().baseUri(base).contentType(ContentType.JSON)
                .body(new UserDto(email, password, null))
                .when().post("/api/auth/login").then();
    }

    @Step("DELETE /api/auth/user")
    public ValidatableResponse delete(String accessToken) {
        return given().baseUri(base).header("Authorization", accessToken)
                .when().delete("/api/auth/user").then();
    }
}
