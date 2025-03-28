package com.atuar.desafio_votacao_sicredi.application.dto.VotingSession;

import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.ListPautaDto;
import com.atuar.desafio_votacao_sicredi.domain.enums.VotingSessionStatusEnum;

import java.time.LocalDateTime;

public record ListVotingSessionDto(
        Long id,
        Integer lifeTimeInMinutes,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Boolean isOpen,
        VotingSessionStatusEnum status,
        ListPautaDto pauta
) {
}
