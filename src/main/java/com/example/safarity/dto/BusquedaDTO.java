package com.example.safarity.dto;

import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import jakarta.persistence.EnumType;
import lombok.Data;

@Data
public class BusquedaDTO {

    private String busqueda;

    private TipoEvento tipoEvento;

    private TipoPago tipoPago;

    private Integer fecha;
}
