package com.example.safarity.model;

import com.example.safarity.model.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuario",schema="safarity",catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "alias")
    private String alias;

    @Column(name = "password")
    private String password;

    @Column(name = "rol")
    @Enumerated(EnumType.ORDINAL)
    private Rol rol;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "usuario", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Participante participante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Organizacion organizacion;

}
