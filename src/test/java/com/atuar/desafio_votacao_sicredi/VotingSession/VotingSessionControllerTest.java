package com.atuar.desafio_votacao_sicredi.VotingSession;

import com.atuar.desafio_votacao_sicredi.Pauta.PautaStub;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
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
@Transactional()
public class VotingSessionControllerTest {

    @LocalServerPort()
    private int port;

    @Autowired()
    private EntityManager em;

    @BeforeEach()
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/v1/api";

        em.createQuery("delete from VotingSession").executeUpdate();
        em.createQuery("delete from Pauta").executeUpdate();
    }

    @Test()
    @DisplayName("Create a Voting Session")
    public void createVotingSession() {
        Integer pautaId = given()
                .contentType("application/json")
                .body(PautaStub.validCreatePautaDto())
                .when()
                .post("/pauta")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        CreateVotingSessionDto createVotingSessionDto = new CreateVotingSessionDto(50, (long) pautaId);

        given()
                .contentType("application/json")
                .body(createVotingSessionDto)
                .when()
                .post("/voting-session")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test()
    @DisplayName("Should throw 404 not found with invalid PautaId")
    public void shouldThrow404NotFoundWithInvalidPautaId() {
        CreateVotingSessionDto createVotingSessionDto = new CreateVotingSessionDto(50, 9999999L);

        given()
                .contentType("application/json")
                .body(createVotingSessionDto)
                .when()
                .post("/voting-session")
                .then()
                .statusCode(404);
    }

    @Test()
    @DisplayName("Should get all VotingSessions")
    public void getAllVotingSessions() {
        Integer pautaId = given()
                .contentType("application/json")
                .body(PautaStub.validCreatePautaDto())
                .when()
                .post("/pauta")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        CreateVotingSessionDto createVotingSessionDto = new CreateVotingSessionDto(50, (long) pautaId);

        given()
                .contentType("application/json")
                .body(createVotingSessionDto)
                .when()
                .post("/voting-session")
                .then()
                .statusCode(200)
                .body("id", notNullValue());

        given()
                .when()
                .get("/voting-session")
                .then()
                .statusCode(200)
                .body("content", notNullValue())
                .body("content.size()", greaterThanOrEqualTo(1));
    }

    @Test()
    @DisplayName("Should get a VotingSession by id")
    public void shouldGetVotingSessionById() {
        Integer pautaId = given()
                .contentType("application/json")
                .body(PautaStub.validCreatePautaDto())
                .when()
                .post("/pauta")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        CreateVotingSessionDto createVotingSessionDto = new CreateVotingSessionDto(50, (long) pautaId);

        Integer votingSessionId = given()
                .contentType("application/json")
                .body(createVotingSessionDto)
                .when()
                .post("/voting-session")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        given()
                .when()
                .get("/voting-session/{id}", votingSessionId)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test()
    @DisplayName("Should throw not found when getting with an invalid VotingSession id")
    public void shouldThrowNotFoundWhenGettingWithInvalidVotingSessionId() {
        given()
                .when()
                .get("/voting-session/{id}", 999999)
                .then()
                .statusCode(404);
    }

    @Test()
    @DisplayName("Delete a VotingSession by id")
    public void deleteVotingSessionById() {
        Integer pautaId = given()
                .contentType("application/json")
                .body(PautaStub.validCreatePautaDto())
                .when()
                .post("/pauta")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        CreateVotingSessionDto createVotingSessionDto = new CreateVotingSessionDto(50, (long) pautaId);

        Integer votingSessionId = given()
                .contentType("application/json")
                .body(createVotingSessionDto)
                .when()
                .post("/voting-session")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        given()
                .when()
                .delete("/voting-session/{id}", votingSessionId)
                .then()
                .statusCode(204);
    }
}
