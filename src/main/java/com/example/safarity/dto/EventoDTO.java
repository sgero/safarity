package com.example.safarity.dto;

import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class EventoDTO {
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

    private Integer entradasDisponibles;

    private Integer entradasVendidas;

    private TipoEvento tipoEvento;

    private TipoPago tipoPago;

    private OrganizacionDTO organizacionDTO;

    private Double totalRecaudado;

}
