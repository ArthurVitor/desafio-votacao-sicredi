package com.atuar.desafio_votacao_sicredi.Vote;

import com.atuar.desafio_votacao_sicredi.domain.entity.Vote;
import com.atuar.desafio_votacao_sicredi.domain.enums.VoteEnum;

public class VoteStub {
    public static Vote validVote() {
        Vote vote = new Vote();
        vote.setId(1L);
        vote.setVote(VoteEnum.YES);

        return vote;
    }
}
