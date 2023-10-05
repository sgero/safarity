package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "asistentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {""})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ticket", fetch = FetchType.LAZY)
    private Asistente asistente;

}
