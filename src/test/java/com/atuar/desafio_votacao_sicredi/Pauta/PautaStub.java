package com.atuar.desafio_votacao_sicredi.Pauta;

import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.ListPautaDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;

public class PautaStub {
    public static Pauta validPauta() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setName("Pauta");
        pauta.setDescription("Pauta description");

        return pauta;
    }

    public static CreatePautaDto validCreatePautaDto() {
        return new CreatePautaDto("Pauta", "Pauta description");
    }

    public static ListPautaDto validListPautaDto() {
        return new ListPautaDto(1L, "Pauta", "Pauta description");
    }
}
