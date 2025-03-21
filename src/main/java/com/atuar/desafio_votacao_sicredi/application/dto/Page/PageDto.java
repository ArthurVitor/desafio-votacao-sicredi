package com.atuar.desafio_votacao_sicredi.application.dto.Page;

import java.util.List;

public record PageDto<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
