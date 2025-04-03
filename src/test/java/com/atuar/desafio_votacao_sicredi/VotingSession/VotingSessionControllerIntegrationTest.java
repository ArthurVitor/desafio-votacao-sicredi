package com.atuar.desafio_votacao_sicredi.VotingSession;

import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class VotingSessionControllerIntegrationTest {
    private static final String BASE_URL = "http://localhost:8080";
    private static CreateVotingSessionDto votingSessionDto;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = "/v1/api";

        votingSessionDto = VotingSessionStub.validCreateVotingSessionDto();
    }

    @Test()
    @DisplayName("Should create an VotingSession")
    void shouldCreateAVotingSession() {
        given()
                .contentType("application/json")
                .body(votingSessionDto)
                .when()
                .post("/voting-session")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Should return all VotingSession with pagination")
    void shouldReturnAllVotingSessions() {
        given()
                .contentType("application/json")
                .when()
                .get("/voting-session")
                .then()
                .statusCode(200)
                .body("content[0].id", notNullValue());
    }

    @Test()
    @DisplayName("Should delete a Voting Session by Id")
    void shouldDeleteAVotingSessionById() {
        Long votingSessionId = 1L;

        given()
                .contentType("application/json")
            .when()
                .delete("/voting-session/{id}", votingSessionId)
            .then()
                .statusCode(204);
    }
}
