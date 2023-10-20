package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name= "asistentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"ticket"})
public class Asistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellidos")
    private String apellidos;

    @Column(name="dni")
    private String dni;

    @Column(name="fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name="telefono")
    private Integer telefono;

    @Column(name="direccion")
    private String direccion;

    @Column(name="email")
    private String email;

    @Column(name = "activo")
    private boolean activo = true;

    @OneToOne
    @JoinColumn(name = "id_ticket", nullable = false)
    private Ticket ticket;


}
