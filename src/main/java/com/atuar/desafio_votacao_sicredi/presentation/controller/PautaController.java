package com.atuar.desafio_votacao_sicredi.presentation.controller;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.CreatePautaDto;
import com.atuar.desafio_votacao_sicredi.application.dto.Pauta.ListPautaDto;
import com.atuar.desafio_votacao_sicredi.application.service.PautaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/pauta")
@RequiredArgsConstructor()
public class PautaController {
    private final PautaService pautaService;

    @PostMapping()
    public ResponseEntity<ListPautaDto> save(@RequestBody() @Valid() CreatePautaDto dto) {
        return ResponseEntity.ok(this.pautaService.create(dto));
    }

    @GetMapping()
    public ResponseEntity<PageDto<ListPautaDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(this.pautaService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListPautaDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.pautaService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.pautaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
