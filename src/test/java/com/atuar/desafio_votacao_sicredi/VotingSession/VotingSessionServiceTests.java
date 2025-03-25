package com.atuar.desafio_votacao_sicredi.VotingSession;

import com.atuar.desafio_votacao_sicredi.Pauta.PautaStub;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.ListVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.VotingSessionMapper;
import com.atuar.desafio_votacao_sicredi.application.service.VotingSessionService;
import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import com.atuar.desafio_votacao_sicredi.domain.repository.PautaRepository;
import com.atuar.desafio_votacao_sicredi.domain.repository.VotingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Voting Session service")
public class VotingSessionServiceTests {
    @Mock()
    private VotingSessionRepository votingSessionRepository;

    @Mock()
    private PautaRepository pautaRepository;

    @Mock()
    private VotingSessionMapper votingSessionMapper;

    @InjectMocks()
    private VotingSessionService votingSessionService;

    private VotingSession votingSession;
    private CreateVotingSessionDto createDto;
    private Pauta pauta;
    private ListVotingSessionDto listDto;

    @BeforeEach()
    public void setUp() {
        votingSession = VotingSessionStub.validVotingSession();
        pauta = PautaStub.validPauta();
        createDto = VotingSessionStub.validCreateVotingSessionDto();
        listDto = VotingSessionStub.validListVotingSession();
    }

    @Test()
    @DisplayName("Succesfully create a VotingSession")
    public void createVotingSession() {
        when(this.pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(this.votingSessionMapper.toEntity(createDto)).thenReturn(votingSession);
        when(this.votingSessionRepository.save(votingSession)).thenReturn(votingSession);
        when(this.votingSessionMapper.toDto(votingSession)).thenReturn(listDto);

        ListVotingSessionDto result = this.votingSessionService.create(createDto);
        assertEquals(listDto, result);
    }

    @Test()
    @DisplayName("Should throw an NotFoundException when an invalid Pauta is choosen")
    public void shouldThrowAnInvalidPautaIsChoosen() {
        org.mockito.Mockito.lenient().when(this.pautaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.votingSessionService.create(createDto));
    }

    @Test()
    @DisplayName("Should return an Voting Session")
    public void shouldReturnAnVotingSession() {
        when(this.votingSessionRepository.findById(1L)).thenReturn(Optional.of(votingSession));
        when(this.votingSessionMapper.toDto(votingSession)).thenReturn(listDto);

        ListVotingSessionDto result = this.votingSessionService.getById(1L);
        assertEquals(listDto, result);
    }

    @Test()
    @DisplayName("Should throw an NotFoundException")
    public void shouldThrowAnNotFoundException() {
        when(this.votingSessionRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> this.votingSessionService.getById(999L));
    }

    @Test()
    @DisplayName("Delete a Voting Session")
    public void deleteVotingSession() {
        doNothing().when(votingSessionRepository).deleteById(1L);
        this.votingSessionService.delete(1L);

        verify(votingSessionRepository, times(1)).deleteById(1L);
    }
}
