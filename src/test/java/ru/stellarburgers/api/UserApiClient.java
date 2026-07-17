package ru.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import ru.stellarburgers.models.UserModel;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class UserApiClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    private final RequestSpecification spec;
    @Setter
    @Getter
    private String accessToken;

    public UserApiClient() {
        RestAssured.baseURI = BASE_URL;
        this.spec = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    @Step("Создать пользователя через API")
    public Response createUser(UserModel user) {
        Response response = spec
                .body(user)
                .post("/api/auth/register");

        if (response.getStatusCode() == SC_OK) {
            accessToken = response.jsonPath().getString("accessToken");
        }

        return response;
    }

    @Step("Удалить пользователя через API")
    public Response deleteUser() {
        if (accessToken == null || accessToken.isEmpty()) {
            return null;
        }

        return spec
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }

    @Step("Войти под пользователем через API")
    public Response loginUser(UserModel user) {
        Response response = spec
                .body(user)
                .post("/api/auth/login");

        if (response.getStatusCode() == SC_OK) {
            accessToken = response.jsonPath().getString("accessToken");
        }

        return response;
    }
}