package com.atuar.desafio_votacao_sicredi.application.mapper;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.ListVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = {PautaMapper.class})
public interface VotingSessionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "pauta", ignore = true)
    @Mapping(target = "isOpen", ignore = true)
    @Mapping(target = "votes", ignore = true)
    VotingSession toEntity(CreateVotingSessionDto dto);

    ListVotingSessionDto toDto(VotingSession session);

    @Mapping(target = "pageNumber", source = "page.number")
    @Mapping(target = "pageSize", source = "page.size")
    PageDto<ListVotingSessionDto> toPageDto(Page<ListVotingSessionDto> page);
}
