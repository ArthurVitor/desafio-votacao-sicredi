package com.atuar.desafio_votacao_sicredi.application.dto.Vote;

import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.ListVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.domain.enums.VoteEnum;

public record ListVoteDto(
        Long id,
        ListUserDto user,
        ListVotingSessionDto votingSession,
        VoteEnum vote
) {
}
