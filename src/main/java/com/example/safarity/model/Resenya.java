package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resenya", schema = "safarity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"evento", "usuario"})
public class Resenya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="estrella")
    private Integer estrella;

    @Column(name="texto")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
