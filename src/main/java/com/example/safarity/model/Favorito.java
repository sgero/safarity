package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evento_participante", schema = "safarity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participante")
    private Participante participante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento")
    private Evento evento;

}
