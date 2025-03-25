package com.atuar.desafio_votacao_sicredi.application.dto.Vote;

public record RemoveVoteDto(
        Long userId,
        Long votingSessionId
) {
}
