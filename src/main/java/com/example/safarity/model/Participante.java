package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "participante", schema = "safarity", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"eventos", "tickets"})
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "dni")
    private String dni;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "activo")
    private boolean activo = true;

    @Column(name="saldo")
    private Double saldo = 0.00;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "evento_participante",
            joinColumns = {@JoinColumn(name = "id_participante", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_evento", nullable = false)})
    private Set<Evento> eventos = new HashSet<>(0);

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "participante", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;
}
