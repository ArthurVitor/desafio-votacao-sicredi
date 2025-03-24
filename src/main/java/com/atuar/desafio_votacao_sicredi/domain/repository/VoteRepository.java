package com.atuar.desafio_votacao_sicredi.domain.repository;

import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import com.atuar.desafio_votacao_sicredi.domain.entity.Vote;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVotingSessionAndUser(VotingSession votingSession, User user);
}
