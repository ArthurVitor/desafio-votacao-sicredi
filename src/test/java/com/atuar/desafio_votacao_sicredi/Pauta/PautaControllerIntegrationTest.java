package com.atuar.desafio_votacao_sicredi.Pauta;

import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class PautaControllerIntegrationTest {
    private static final String BASE_URL = "http://localhost:8080";
    private static CreatePautaDto createDto;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = "/v1/api";

        createDto = PautaStub.validCreatePautaDto();
    }

    @Test
    @DisplayName("Should create a Pauta")
    void testCreatePauta() {
        given()
            .contentType("application/json")
                .body(createDto)
            .when()
                .post("/pauta")
            .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Should return all Pautas with pagination")
    void testGetAllPautas() {
        given()
                .contentType("application/json")
                    .when()
                .get("/pauta")
                    .then()
                    .statusCode(200)
                .body("content", notNullValue())
                .body("content.size()", not(0))
                .body("content[0].id", notNullValue())
                .body("content[0].name", notNullValue())
                .body("content[0].description", notNullValue())
                .body("pageNumber", notNullValue())
                .body("pageSize", notNullValue())
                .body("totalElements", notNullValue())
                .body("totalPages", notNullValue());
    }

    @Test
    @DisplayName("Should delete a Pauta by ID")
    void testDeletePauta() {
        Long pautaId = 1L;

        given()
                .contentType("application/json")
                .when()
                .delete("/pauta/{id}", pautaId)
                .then()
                .statusCode(204);
    }
}
