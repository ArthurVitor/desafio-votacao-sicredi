package com.atuar.desafio_votacao_sicredi.presentation.controller;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.ListVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.service.VotingSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/voting-session")
@RequiredArgsConstructor()
public class VotingSessionController {
    private final VotingSessionService votingSessionService;

    @PostMapping()
    public ResponseEntity<ListVotingSessionDto> create(@RequestBody() @Valid() CreateVotingSessionDto listPautaDto) {
        return ResponseEntity.ok(votingSessionService.create(listPautaDto));
    }

    @GetMapping()
    public ResponseEntity<PageDto<ListVotingSessionDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(votingSessionService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListVotingSessionDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(votingSessionService.getById(id));
    }
}

