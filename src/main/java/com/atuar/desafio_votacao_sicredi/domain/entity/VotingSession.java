package com.atuar.desafio_votacao_sicredi.domain.entity;

import com.atuar.desafio_votacao_sicredi.domain.enums.VotingSessionStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false)
    private Boolean isOpen = true;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @OneToMany(mappedBy = "votingSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> votes;

    @Column(nullable = false)
    private VotingSessionStatusEnum status = VotingSessionStatusEnum.ONGOING;
}
