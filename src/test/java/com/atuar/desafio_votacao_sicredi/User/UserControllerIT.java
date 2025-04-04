package com.atuar.desafio_votacao_sicredi.User;

import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.service.UserService;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
class UserControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/v1/api";

        entityManager.createQuery("delete from User").executeUpdate();

        userService.create(new CreateUserDto("Arthur", "email.com"));
    }

    @Test
    @DisplayName("Should create an user")
    void shouldCreateAnUser() {
        var createUserDto = UserStub.validCreateUserDto();

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
                .body("content.size()", greaterThanOrEqualTo(0))
                .body("pageNumber", notNullValue())
                .body("pageSize", notNullValue())
                .body("totalElements", notNullValue())
                .body("totalPages", notNullValue());
    }

    @Test
    @DisplayName("Should delete an User by Id")
    void shouldDeleteAnUserById() {
        var userId = 1;

        given()
                .contentType("application/json")
                .when()
                .delete("/user/{id}", userId)
                .then()
                .statusCode(204);
    }
}
