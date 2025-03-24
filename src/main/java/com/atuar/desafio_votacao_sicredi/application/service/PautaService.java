package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.ListPautaDto;
import com.atuar.desafio_votacao_sicredi.application.mapper.PautaMapper;
import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;
import com.atuar.desafio_votacao_sicredi.domain.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service()
@RequiredArgsConstructor()
public class PautaService {
    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    public ListPautaDto create(CreatePautaDto createPautaDto) {
        Pauta entity = this.pautaMapper.toEntity(createPautaDto);

        return this.pautaMapper.toDto(this.pautaRepository.save(entity));
    }
}
