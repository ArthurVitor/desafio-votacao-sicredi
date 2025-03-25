package com.atuar.desafio_votacao_sicredi.Pauta;

import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.ListPautaDto;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.PautaMapper;
import com.atuar.desafio_votacao_sicredi.application.service.PautaService;
import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;
import com.atuar.desafio_votacao_sicredi.domain.repository.PautaRepository;
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
import static org.mockito.Mockito.*;

@DisplayName("Pauta Service")
@ExtendWith(MockitoExtension.class)
public class PautaServiceTests {
    @Mock()
    private PautaRepository pautaRepository;

    @Mock()
    private PautaMapper pautaMapper;

    @InjectMocks()
    private PautaService pautaService;

    private Pauta pauta;
    private CreatePautaDto createDto;
    private ListPautaDto listDto;

    @BeforeEach()
    void setUp() {
        pauta = PautaStub.validPauta();
        createDto = PautaStub.validCreatePautaDto();
        listDto = PautaStub.validListPautaDto();
    }

    @Test()
    @DisplayName("Succesfully create a Pauta")
    public void createPauta() {
        when(pautaMapper.toEntity(createDto)).thenReturn(pauta);
        when(pautaRepository.save(pauta)).thenReturn(pauta);
        when(pautaMapper.toDto(pauta)).thenReturn(listDto);

        ListPautaDto result = this.pautaService.create(createDto);

        assertEquals(listDto, result);
    }

    @Test()
    @DisplayName("Should return an user")
    public void shouldReturnUser() {
        Long userId = 1L;
        when(pautaRepository.findById(userId)).thenReturn(Optional.of(pauta));
        when(pautaMapper.toDto(pauta)).thenReturn(listDto);

        ListPautaDto result = this.pautaService.getById(userId);
        assertEquals(listDto, result);
    }

    @Test()
    @DisplayName("Should throw an NotFoundException")
    public void shouldThrowNotFoundException() {
        Long userId = 999L;
        when(pautaRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.pautaService.getById(userId));
    }

    @Test()
    @DisplayName("Should delete an user")
    public void shouldDeletePauta() {
        Long userId = 1L;

        doNothing().when(pautaRepository).deleteById(userId);

        this.pautaService.delete(userId);

        verify(pautaRepository, times(1)).deleteById(userId);
    }
}
