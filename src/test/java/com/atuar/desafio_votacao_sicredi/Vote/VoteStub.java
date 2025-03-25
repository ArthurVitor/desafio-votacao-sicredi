package com.atuar.desafio_votacao_sicredi.Vote;

import com.atuar.desafio_votacao_sicredi.User.UserStub;
import com.atuar.desafio_votacao_sicredi.VotingSession.VotingSessionStub;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.CreateVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.ListVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.RemoveVoteDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.Vote;
import com.atuar.desafio_votacao_sicredi.domain.enums.VoteEnum;

public class VoteStub {
    public static Vote validVote() {
        Vote vote = new Vote();
        vote.setId(1L);
        vote.setVote(VoteEnum.YES);

        return vote;
    }

    public static CreateVoteDto validCreateVoteDto() {
        return new CreateVoteDto(1L, 1L, VoteEnum.YES);
    }

    public static ListVoteDto validListVoteDto() {
        return new ListVoteDto(1L, UserStub.validListUserDto(), VotingSessionStub.validListVotingSession(), VoteEnum.YES);
    }

    public static RemoveVoteDto validRemoveVoteDto() {
        return new RemoveVoteDto(1L, 1L);
    }
}
