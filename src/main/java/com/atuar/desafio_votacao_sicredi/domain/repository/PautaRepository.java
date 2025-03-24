package com.atuar.desafio_votacao_sicredi.domain.repository;

import com.atuar.desafio_votacao_sicredi.domain.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
