package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.Vote.CreateVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.ListVoteDto;
import com.atuar.desafio_votacao_sicredi.application.exception.BadRequestException;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.VoteMapper;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import com.atuar.desafio_votacao_sicredi.domain.entity.Vote;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import com.atuar.desafio_votacao_sicredi.domain.repository.UserRepository;
import com.atuar.desafio_votacao_sicredi.domain.repository.VoteRepository;
import com.atuar.desafio_votacao_sicredi.domain.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final VotingSessionRepository votingSessionRepository;
    private final UserRepository userRepository;
    private final VoteMapper voteMapper;

    public ListVoteDto vote(CreateVoteDto dto) {
        VotingSession votingSession = findVotingSessionById(dto.votingSessionId());
        User user = findUserById(dto.userId());

        if (this.voteRepository.existsByVotingSessionAndUser(votingSession, user)) {
            throw new BadRequestException("User already voted on this session");
        }

        Vote vote = new Vote(user, votingSession, dto.vote());
        return voteMapper.toDto(this.voteRepository.save(vote));
    }

    private VotingSession findVotingSessionById(Long id) {
        return votingSessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find voting session with id: " + id));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id: " + id));
    }
}
