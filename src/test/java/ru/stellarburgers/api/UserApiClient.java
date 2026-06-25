package ru.stellarburgers.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class UserApiClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    private final RequestSpecification spec;
    private String accessToken;

    public UserApiClient() {
        RestAssured.baseURI = BASE_URL;
        this.spec = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    public Response createUser(String email, String password, String name) {
        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("name", name);

        Response response = spec
                .body(userData)
                .post("/api/auth/register");

        if (response.getStatusCode() == SC_OK) {
            accessToken = response.jsonPath().getString("accessToken");
        }

        return response;
    }

    public Response deleteUser() {
        if (accessToken == null || accessToken.isEmpty()) {
            return null;
        }

        return spec
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }

    public Response loginUser(String email, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        Response response = spec
                .body(credentials)
                .post("/api/auth/login");

        if (response.getStatusCode() == SC_OK) {
            accessToken = response.jsonPath().getString("accessToken");
        }

        return response;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
