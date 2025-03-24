package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.ListVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFound.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.VotingSessionMapper;
import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import com.atuar.desafio_votacao_sicredi.domain.repository.PautaRepository;
import com.atuar.desafio_votacao_sicredi.domain.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service()
@RequiredArgsConstructor()
public class VotingSessionService {
    private final VotingSessionRepository votingSessionRepository;
    private final PautaRepository pautaRepository;
    private final VotingSessionMapper mapper;

    public ListVotingSessionDto create(CreateVotingSessionDto dto) {
        Pauta pauta = this.pautaRepository.findById(dto.pautaId())
                .orElseThrow(() -> new NotFoundException("Couldn't find Pauta with id " + dto.pautaId()));

        VotingSession votingSession = this.mapper.toEntity(dto);
        votingSession.setPauta(pauta);

        LocalDateTime now = LocalDateTime.now();
        votingSession.setStartTime(now);
        votingSession.setEndTime(now.plusDays(dto.lifeTimeInMinutes()));

        return this.mapper.toDto(votingSessionRepository.save(votingSession));
    }
}
