package com.atuar.desafio_votacao_sicredi.VotingSession;

import com.atuar.desafio_votacao_sicredi.Pauta.PautaStub;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.ListVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import com.atuar.desafio_votacao_sicredi.domain.enums.VotingSessionStatusEnum;

import java.time.LocalDateTime;

public class VotingSessionStub {
    public static VotingSession validVotingSession() {
        VotingSession votingSession = new VotingSession();
        votingSession.setLifeTimeInMinutes(5);
        votingSession.setStartTime(LocalDateTime.of(2023, 5, 15, 10, 30, 45));
        votingSession.setEndTime(LocalDateTime.now().plusMinutes(5));
        votingSession.setPauta(PautaStub.validPauta());

        return votingSession;
    }

    public static ListVotingSessionDto validListVotingSession() {
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 15, 10, 30, 45);
        return new ListVotingSessionDto(1L, 5, startTime, startTime.plusMinutes(5), true, VotingSessionStatusEnum.ONGOING, PautaStub.validListPautaDto());
    }

    public static CreateVotingSessionDto validCreateVotingSessionDto() {
        return new CreateVotingSessionDto(5, 1L);
    }
}
