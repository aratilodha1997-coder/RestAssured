package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Response getRequest(String endpoint) {

        return given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response postRequest(String endpoint,
                                       Object payload) {

        return given()
                .contentType("application/json")
                .body(payload)

                .when()
                .post(endpoint)

                .then()
                .extract()
                .response();
    }

    public static Response putRequest(String endpoint,
                                      Object payload,
                                      int id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(payload)

                .when()
                .put(endpoint)

                .then()
                .extract()
                .response();
    }

    public static Response deleteRequest(String endpoint, long id)
    {
        return given()
                .pathParam("id", id)

                .when()
                .delete(endpoint)

                .then()
                .extract()
                .response();
    }
}