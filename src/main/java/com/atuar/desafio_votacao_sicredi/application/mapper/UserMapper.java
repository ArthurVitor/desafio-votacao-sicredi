package com.atuar.desafio_votacao_sicredi.application.mapper;


import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUserDto dto);

    ListUserDto toListDto(User user);

    @Mapping(target = "pageNumber", source = "page.number")
    @Mapping(target = "pageSize", source = "page.size")
    PageDto<ListUserDto> toPageDto(Page<ListUserDto> page);
}
