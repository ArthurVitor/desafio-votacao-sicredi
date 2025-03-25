package com.atuar.desafio_votacao_sicredi.presentation.controller;

import com.atuar.desafio_votacao_sicredi.application.dto.Vote.CreateVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.ListVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.RemoveVoteDto;
import com.atuar.desafio_votacao_sicredi.application.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/vote")
@RequiredArgsConstructor()
public class VoteController {
    private final VoteService voteService;

    @PostMapping()
    public ResponseEntity<ListVoteDto> vote(@RequestBody() @Valid() CreateVoteDto dto) {
        return ResponseEntity.ok(this.voteService.vote(dto));
    }

    @PostMapping("/unvote")
    public ResponseEntity<String> unvote(@RequestBody() @Valid() RemoveVoteDto dto) {
        this.voteService.unvote(dto);

        return ResponseEntity.ok("Unvoted successfully");
    }
}
