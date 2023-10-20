package com.example.safarity.converter;

import com.example.safarity.dto.EventoDTO;
import com.example.safarity.model.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EventoMapper {


    @Mapping(source = "fecha_lanzamiento", target = "fechaLanzamiento", qualifiedByName = "conversorStringFecha")
    @Mapping(source = "fecha_venta", target = "fechaVentaDisponible", qualifiedByName = "conversorStringFecha")
    @Mapping(source = "fecha_inicio", target = "fechaInicio", qualifiedByName = "conversorStringFecha")
    @Mapping(source = "fecha_fin", target = "fechaFin", qualifiedByName = "conversorStringFecha")
    public abstract Evento toEntity(EventoDTO dto);

    @Mapping(source = "fechaLanzamiento", target = "fecha_lanzamiento", qualifiedByName = "conversorFechaString")
    @Mapping(source = "fechaVentaDisponible", target = "fecha_venta", qualifiedByName = "conversorFechaString")
    @Mapping(source = "fechaInicio", target = "fecha_inicio", qualifiedByName = "conversorFechaString")
    @Mapping(source = "fechaFin", target = "fecha_fin", qualifiedByName = "conversorFechaString")
    public abstract EventoDTO toDTO (Evento entity);

    public abstract List<EventoDTO> toEntity (List<EventoDTO> dto);

    public abstract List<EventoDTO> toDTO (List<Evento> entity);

    @Named(value = "conversorFechaString")
    public String LocalDateToString(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    @Named(value = "conversorStringFecha")
    public LocalDate StringToLocalDate(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha,formatter);
    }

}
