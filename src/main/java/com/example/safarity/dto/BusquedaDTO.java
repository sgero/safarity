package com.example.safarity.dto;

import com.example.safarity.model.enums.TipoEvento;
import jakarta.persistence.EnumType;
import lombok.Data;

@Data
public class BusquedaDTO {

    private String busqueda;

    private TipoEvento tipoEvento;
}
