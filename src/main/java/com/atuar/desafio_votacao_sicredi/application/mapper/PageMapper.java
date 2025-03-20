package com.atuar.desafio_votacao_sicredi.application.mapper;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import org.springframework.data.domain.Page;

public class PageMapper {
    public static <T> PageDto<T> toPageDto(Page<T> page) {
        return new PageDto<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
