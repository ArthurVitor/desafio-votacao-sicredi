package com.atuar.desafio_votacao_sicredi.domain.entity;

import com.atuar.desafio_votacao_sicredi.domain.enums.VoteEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@AllArgsConstructor()
@NoArgsConstructor()
@Getter()
@Setter()
@Table(name = "tb_vote")
public class Vote {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "votingSession_id", nullable = false)
    private VotingSession votingSession;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoteEnum vote;

    public Vote(User user, VotingSession votingSession, VoteEnum vote) {
        this.user = user;
        this.votingSession = votingSession;
        this.vote = vote;
    }
}
