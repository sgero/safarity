package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name= "asistentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {""})
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
    private Date fecha_nacimiento;

    @Column(name="telefono")
    private Integer telefono;

    @Column(name="direccion")
    private String direccion;

    @Column(name="email")
    private String email;

    @OneToOne
    @JoinColumn(name = "id_ticket", nullable = false)
    private Ticket ticket;

    @Column(name = "activo")
    @Builder.Default
    private boolean activo = true;
}
