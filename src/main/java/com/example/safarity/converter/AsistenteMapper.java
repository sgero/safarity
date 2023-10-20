package com.example.safarity.converter;

import com.example.safarity.dto.AsistenteDTO;
import com.example.safarity.model.Asistente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AsistenteMapper {

    @Mapping(source = "fecha_nacimiento", target = "fechaNacimiento", qualifiedByName = "conversorStringFecha")
    Asistente toEntity(AsistenteDTO dto);
    @Mapping(source = "fechaNacimiento", target = "fecha_nacimiento", qualifiedByName = "conversorFechaString")
    AsistenteDTO toDTO(Asistente entity);

    List<Asistente> toEntity(List<AsistenteDTO> entitys);

    List<AsistenteDTO> toDTO(List<Asistente> dtos);

    @Named(value ="conversorFechaString")
    default String LocalDateToString(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    @Named(value ="conversorStringFecha")
    default LocalDate StringToLocalDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, formatter);
    }

}
