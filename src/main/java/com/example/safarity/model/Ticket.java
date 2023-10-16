package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@Table(name = "ticket" , schema = "safarity" , catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "es_disponible")
    private String esDisponible;

    @Column(name = "dinero_aportado")
    private Integer dineroAportado;

    @Column(name = "fecha_compra")
    private LocalDate fecha;


    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name="id_participante", nullable = false)
    private Participante participante;


    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;



}
