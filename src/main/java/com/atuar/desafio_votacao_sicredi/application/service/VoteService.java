package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.Vote.CreateVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.ListVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.RemoveVoteDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final VotingSessionRepository votingSessionRepository;
    private final UserRepository userRepository;
    private final VoteMapper voteMapper;

    public ListVoteDto vote(CreateVoteDto dto) {
        VotingSession votingSession = findVotingSessionById(dto.votingSessionId());
        isSessionValid(votingSession);

        User user = findUserById(dto.userId());
        userAlreadyVoted(votingSession, user);

        Vote vote = new Vote(user, votingSession, dto.vote());
        return voteMapper.toDto(voteRepository.save(vote));
    }

    @Transactional()
    public void unvote(RemoveVoteDto dto) {
        VotingSession votingSession = findVotingSessionById(dto.votingSessionId());
        User user = findUserById(dto.userId());

        isSessionValid(votingSession);

        this.voteRepository.deleteByVotingSessionAndUser(votingSession, user);
    }

    private VotingSession findVotingSessionById(Long id) {
        return votingSessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find voting session with id: " + id));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id: " + id));
    }

    private void isSessionValid(VotingSession votingSession) {
        if (votingSession.getEndTime().isBefore(LocalDateTime.now()) || !votingSession.getIsOpen()) {
            throw new BadRequestException("Session already finished");
        }
    }

    private void userAlreadyVoted(VotingSession votingSession, User user) {
        if (voteRepository.existsByVotingSessionAndUser(votingSession, user)) {
            throw new BadRequestException("User already voted on this session");
        }
    }
}
