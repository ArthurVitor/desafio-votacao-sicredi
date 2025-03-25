package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import com.atuar.desafio_votacao_sicredi.domain.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VotingSessionScheduler {
    private final VotingSessionRepository votingSessionRepository;

    @Scheduled(fixedRate = 60000)
    public void closeSessions() {
        LocalDateTime now = LocalDateTime.now();
        List<VotingSession> expiredSessions = votingSessionRepository.findExpiredSessions(now);

        if (expiredSessions.isEmpty()) {
            return;
        }

        expiredSessions.forEach(session -> {
            session.setStatus(VotingSessionService.calculateSessionResult(session));
            session.setIsOpen(false);
        });

        votingSessionRepository.saveAll(expiredSessions);
    }
}
