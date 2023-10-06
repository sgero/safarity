package com.example.safarity.model;

import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name="evento", schema="safarity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="direccion")
    private String direccion;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="imagen")
    private String imagen;

    @Column(name="aforo")
    private int aforo;

    @Column(name="total_asistentes")
    private int totalAsistentes;

    @Column(name="fecha_lanzamiento")
    private Date fechaLanzamiento;

    @Column(name="fecha_venta_disponible")
    private Date fechaVentaDisponible;

    @Column(name="fecha_inicio")
    private Date fechaInicio;

    @Column(name="fecha_fin")
    private Date fechaFin;

    @Column(name="entradas_vendidas")
    private int entradasVendidas;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

}
