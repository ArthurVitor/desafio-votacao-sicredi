package com.atuar.desafio_votacao_sicredi.application.mapper;

import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.CreateVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.application.dto.VotingSession.ListVotingSessionDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.VotingSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PautaMapper.class})
public interface VotingSessionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "pauta", ignore = true)
    VotingSession toEntity(CreateVotingSessionDto dto);

    ListVotingSessionDto toDto(VotingSession session);
}
