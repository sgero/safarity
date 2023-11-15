package com.example.safarity.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organizacion", schema = "safarity", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"eventos"})
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "cif")
    private String cif;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_fundacion")
    private LocalDate fechaFundacion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "info")
    private String info;

    @Column(name = "sitio_web")
    private String sitioWeb;

    @Column(name = "logo")
    private String logo;

    @Column(name = "activo")
    private boolean activo = true;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizacion", fetch = FetchType.LAZY)
    private Set<Evento> eventos = new HashSet<>();


}
