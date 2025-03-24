package com.atuar.desafio_votacao_sicredi.application.dto.VotingSession;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateVotingSessionDto(
        @NotNull()
        @Size(min = 1, max = 59)
        Integer lifeTimeInMinutes,
        @NotNull()
        Long pautaId
) {
}
