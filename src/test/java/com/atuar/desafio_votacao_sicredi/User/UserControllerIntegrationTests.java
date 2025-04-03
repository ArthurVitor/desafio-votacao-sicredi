package com.atuar.desafio_votacao_sicredi.User;

import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class UserControllerIntegrationTests {
    private static final String BASE_URL = "http://localhost:8080";
    private static CreateUserDto createUserDto;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = "/v1/api";

        createUserDto = UserStub.validCreateUserDto();
    }

    @Test()
    @DisplayName("Should create an user")
    void shouldCreateAnUser() {
        given()
                .contentType("application/json")
                .body(createUserDto)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Should return all User with pagination")
    void shouldReturnAllUserWithPagination() {
        given()
                .contentType("application/json")
                .when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("content", notNullValue())
                .body("content.size()", not(0))
                .body("content[0].id", notNullValue())
                .body("content[0].username", notNullValue())
                .body("pageNumber", notNullValue())
                .body("pageSize", notNullValue())
                .body("totalElements", notNullValue())
                .body("totalPages", notNullValue());
    }

    @Test()
    @DisplayName("Should delete an User by Id")
    void shouldDeleteAnUserById() {
        Long userId = 1L;

        given()
                .contentType("application/json")
                .when()
                .delete("/user/{id}", userId)
                .then()
                .statusCode(204);
    }
}
