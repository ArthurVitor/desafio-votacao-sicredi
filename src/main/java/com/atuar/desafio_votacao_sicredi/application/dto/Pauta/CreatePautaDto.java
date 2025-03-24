package com.atuar.desafio_votacao_sicredi.application.dto.Pauta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePautaDto(
        @NotNull()
        @NotBlank()
        @Size(min = 5, max = 50)
        String name,
        
        @Size(max = 50)
        String description
) {
}
