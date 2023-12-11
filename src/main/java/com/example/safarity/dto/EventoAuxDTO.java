package com.example.safarity.dto;

import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import lombok.Data;

@Data
public class EventoAuxDTO {

    private Long id;

    private String nombre;

    private String direccion;

    private String descripcion;

    private String imagen;

    private Double precio;

    private Integer aforo;

    private String fecha_lanzamiento;

    private String fecha_venta;

    private String fecha_inicio;

    private String fecha_fin;

    private String tipoEvento;

    private String tipoPago;

    private String organizacion;
}
