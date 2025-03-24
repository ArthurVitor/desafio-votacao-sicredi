package com.atuar.desafio_votacao_sicredi.domain.repository;

import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
}
