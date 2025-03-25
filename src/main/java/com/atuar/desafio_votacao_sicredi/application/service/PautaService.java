package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.ListPautaDto;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.PautaMapper;
import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;
import com.atuar.desafio_votacao_sicredi.domain.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public PageDto<ListPautaDto> getAll(Pageable pageable) {
        Page<ListPautaDto> page = this.pautaRepository.findAll(pageable).map(pautaMapper::toDto);

        return this.pautaMapper.toPageDto(page);
    }

    public ListPautaDto getById(Long id) {
       return this.pautaMapper.toDto(this.pautaRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find pauta with id: " + id)));
    }

    public void delete(Long id) {
        this.pautaRepository.deleteById(id);
    }
}
