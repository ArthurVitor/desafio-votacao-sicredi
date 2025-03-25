package com.atuar.desafio_votacao_sicredi.application.mapper;

import com.atuar.desafio_votacao_sicredi.application.dto.Vote.ListVoteDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VotingSessionMapper.class, UserMapper.class})
public interface VoteMapper {
    @Mapping(target = "id", source = "vote.id")
    ListVoteDto toDto(Vote vote);
}
