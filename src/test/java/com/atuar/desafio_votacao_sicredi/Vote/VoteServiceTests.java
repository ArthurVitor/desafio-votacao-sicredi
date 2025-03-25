package com.atuar.desafio_votacao_sicredi.Vote;

import com.atuar.desafio_votacao_sicredi.User.UserStub;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.CreateVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.ListVoteDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Vote.RemoveVoteDto;
import com.atuar.desafio_votacao_sicredi.application.exception.BadRequestException;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.VoteMapper;
import com.atuar.desafio_votacao_sicredi.application.service.VoteService;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import com.atuar.desafio_votacao_sicredi.domain.entity.Vote;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import com.atuar.desafio_votacao_sicredi.domain.repository.UserRepository;
import com.atuar.desafio_votacao_sicredi.domain.repository.VoteRepository;
import com.atuar.desafio_votacao_sicredi.domain.repository.VotingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Vote service tests")
public class VoteServiceTests {
    @Mock()
    private VoteRepository voteRepository;

    @Mock()
    private VotingSessionRepository votingSessionRepository;

    @Mock()
    private UserRepository userRepository;

    @Mock()
    private VoteMapper voteMapper;

    @InjectMocks()
    private VoteService voteService;

    private VotingSession votingSession;
    private User user;
    private Vote vote;
    private ListVoteDto listDto;
    private CreateVoteDto createVoteDto;
    private RemoveVoteDto removeVoteDto;

    @BeforeEach()
    public void setUp() {
        votingSession = Mockito.mock(VotingSession.class);
        user = UserStub.validUser();
        vote = VoteStub.validVote();
        listDto = VoteStub.validListVoteDto();
        createVoteDto = VoteStub.validCreateVoteDto();
        removeVoteDto = VoteStub.validRemoveVoteDto();
    }

    @Test
    void shouldUnvoteSuccessfully() {
        when(votingSessionRepository.findById(removeVoteDto.votingSessionId())).thenReturn(Optional.of(votingSession));
        when(userRepository.findById(removeVoteDto.userId())).thenReturn(Optional.of(user));
        when(votingSession.getEndTime()).thenReturn(LocalDateTime.now().plusMinutes(10));
        when(votingSession.getIsOpen()).thenReturn(true);

        assertDoesNotThrow(() -> voteService.unvote(removeVoteDto));
        verify(voteRepository, times(1)).deleteByVotingSessionAndUser(votingSession, user);
    }


    @Test
    void shouldFailWhenVotingSessionIsClosed() {
        when(votingSessionRepository.findById(any())).thenReturn(Optional.of(votingSession));
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        lenient().when(votingSession.getEndTime()).thenReturn(LocalDateTime.now().minusMinutes(10));

        assertThrows(BadRequestException.class, () -> voteService.unvote(removeVoteDto));
    }


    @Test
    void shouldVoteSuccessfully() {
        Long votingSessionId = 1L;
        Long userId = 1L;

        when(votingSessionRepository.findById(votingSessionId)).thenReturn(Optional.of(votingSession));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(votingSession.getEndTime()).thenReturn(LocalDateTime.now().plusMinutes(10));
        when(votingSession.getIsOpen()).thenReturn(true);
        when(voteRepository.existsByVotingSessionAndUser(votingSession, user)).thenReturn(false);
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);
        when(voteMapper.toDto(vote)).thenReturn(listDto);

        ListVoteDto result = voteService.vote(createVoteDto);

        assertNotNull(result);
        assertEquals(listDto, result);
    }

    @Test
    void shouldFailWhenVotingSessionNotFound() {
        when(votingSessionRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> voteService.vote(createVoteDto));
    }

    @Test
    void shouldFailWhenUserNotFound() {
        when(votingSessionRepository.findById(any())).thenReturn(Optional.of(votingSession));
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        when(votingSession.getEndTime()).thenReturn(LocalDateTime.now().plusMinutes(10));
        when(votingSession.getIsOpen()).thenReturn(true);

        assertThrows(NotFoundException.class, () -> voteService.vote(createVoteDto));
    }
}
