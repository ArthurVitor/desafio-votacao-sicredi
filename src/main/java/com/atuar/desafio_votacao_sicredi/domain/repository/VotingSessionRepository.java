package com.atuar.desafio_votacao_sicredi.domain.repository;

import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
    @Query("SELECT v FROM VotingSession v WHERE v.endTime < :currentTime")
    List<VotingSession> findExpiredSessions(@Param("currentTime") LocalDateTime currentTime);
}
