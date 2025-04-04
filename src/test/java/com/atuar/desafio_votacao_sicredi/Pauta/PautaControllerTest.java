package com.atuar.desafio_votacao_sicredi.Pauta;

import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
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
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional()
public class PautaControllerTest {
    @LocalServerPort()
    private int port;

    @Autowired()
    private EntityManager entityManager;

    @BeforeEach()
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/v1/api";

        entityManager.createQuery("delete from Pauta").executeUpdate();
    }

    @Test()
    @DisplayName("Should create a Pauta")
    public void createPauta() {
        var createPautaDto = PautaStub.validCreatePautaDto();

        given()
                .contentType("application/json")
                .body(createPautaDto)
                .when()
                .post("/pauta")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test()
    @DisplayName("Should throw throw 400 when creating with null values")
    public void createPautaWithNullValues() {
        var createPautaDto = new CreatePautaDto(null, "descricao");

        given()
                .contentType("application/json")
                .body(createPautaDto)
                .when()
                .post("/pauta")
                .then()
                .statusCode(400);
    }

    @Test()
    @DisplayName("Should return all Pautas")
    public void getAllPautas() {
        var createPautaDto = PautaStub.validCreatePautaDto();

        given()
                .contentType("application/json")
                .body(createPautaDto)
                .when()
                .post("/pauta")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/pauta")
                .then()
                .statusCode(200)
                .body("content", notNullValue())
                .body("content.size()", greaterThanOrEqualTo(1));
    }

    @Test()
    @DisplayName("Should return no Pautas")
    public void getNoPauta() {
        given()
                .when()
                .get("/pauta")
                .then()
                .statusCode(200)
                .body("content", nullValue());
    }

    @Test()
    @DisplayName("Should return a pauta when get by id")
    public void getPautaById() {
        CreatePautaDto createPautaDto = PautaStub.validCreatePautaDto();
        Integer pautaId =
                given()
                        .contentType("application/json")
                        .body(createPautaDto)
                        .when()
                        .post("/pauta")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        given()
                .when()
                .get("/pauta/{id}", pautaId)
                .then()
                .statusCode(200)
                .body("id", equalTo(pautaId));
    }

    @Test()
    @DisplayName("Should throw 404 not found when getting invalid Pauta")
    public void getInvalidPauta() {
        Long pautaId = 9999999L;

        given()
                .when()
                .get("/pauta/{id}", pautaId)
                .then()
                .statusCode(404)
                .body("message", equalTo("Couldn't find pauta with id: " + pautaId));
    }

    @Test()
    @DisplayName("Should delete a pauta")
    public void deletePauta() {
        given()
                .when()
                .delete("/pauta/{id}", 1)
                .then()
                .statusCode(204);
    }
}
