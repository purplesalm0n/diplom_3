package ru.praktikum.stellarburgers.ui.steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.stellarburgers.ui.config.Endpoints;
import ru.praktikum.stellarburgers.ui.config.Env;

public class ApiUserSteps {

    public ApiUserSteps() {
        RestAssured.baseURI = Env.baseUrl();
        RestAssured.filters(new AllureRestAssured());
    }

    @Step("API: регистрация пользователя")
    public ValidatableResponse register(String email, String password, String name) {
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}", email, password, name);
        return RestAssured.given().contentType(ContentType.JSON).body(body)
                .post(Endpoints.REGISTER).then();
    }

    @Step("API: логин и получение accessToken")
    public String loginAndGetAccessToken(String email, String password) {
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
        return RestAssured.given().contentType(ContentType.JSON).body(body)
                .post(Endpoints.LOGIN).then()
                .extract().path("accessToken");
    }

    @Step("API: попытка логина, вернуть токен или null")
    public String tryLoginGetTokenOrNull(String email, String password) {
        try {
            String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
            return RestAssured.given().contentType(ContentType.JSON).body(body)
                    .post(Endpoints.LOGIN).then()
                    .extract().path("accessToken");
        } catch (Exception ignored) {
            return null;
        }
    }

    @Step("API: удалить пользователя")
    public void delete(String accessToken) {
        if (accessToken == null || accessToken.isBlank()) return;
        RestAssured.given().header("Authorization", accessToken)
                .delete(Endpoints.USER).then();
    }
}
