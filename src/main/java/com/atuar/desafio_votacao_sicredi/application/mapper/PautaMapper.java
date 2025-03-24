package com.atuar.desafio_votacao_sicredi.application.mapper;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.ListPautaDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    @Mapping(target = "id", ignore = true)
    Pauta toEntity(CreatePautaDto createPautaDto);

    ListPautaDto toDto(Pauta pauta);

    @Mapping(target = "pageNumber", source = "page.number")
    @Mapping(target = "pageSize", source = "page.size")
    PageDto<ListPautaDto> toPageDto(Page<ListPautaDto> page);
}
