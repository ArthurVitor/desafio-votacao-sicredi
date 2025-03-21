package com.atuar.desafio_votacao_sicredi.domain.repository;

import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
