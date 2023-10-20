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
public interface EventoMapper {

    @Mapping(source = "fecha_lanzamiento", target = "fechaLanzamiento", qualifiedByName = "conversorFechaString")
    Evento toEntity(EventoDTO dto);

    @Mapping(source = "fechaLanzamiento", target = "fecha_lanzamiento", qualifiedByName = "conversorFechaString")
    EventoDTO toDTO (Evento entity);

    List<EventoDTO> toEntity (List<EventoDTO> dto);

    List<EventoDTO> toDTO (List<Evento> entity);

    @Named(value ="conversorFechaString")
    private String LocalDateToString(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    @Named(value ="conversorStringFecha")
    default LocalDate StringToLocalDate(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha,formatter);
    }

}
