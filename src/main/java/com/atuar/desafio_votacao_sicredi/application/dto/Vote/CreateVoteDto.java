package com.atuar.desafio_votacao_sicredi.application.dto.Vote;

import com.atuar.desafio_votacao_sicredi.domain.enums.VoteEnum;
import jakarta.validation.constraints.NotNull;

public record CreateVoteDto(
        @NotNull()
        Long userId,
        @NotNull()
        Long votingSessionId,
        @NotNull()
        VoteEnum vote
) {
}
