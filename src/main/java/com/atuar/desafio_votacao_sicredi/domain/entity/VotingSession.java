package com.atuar.desafio_votacao_sicredi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity()
@AllArgsConstructor()
@NoArgsConstructor()
@Getter()
@Setter()
@Table(name = "tb_votingSession")
public class VotingSession {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer lifeTimeInMinutes;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;
}
