package com.example.safarity.converter;

import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.model.Participante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipanteMapper {

    @Mapping(source = "fecha_nacimiento", target = "fechaNacimiento", qualifiedByName = "conversorStringFecha")
    Participante toEntity(ParticipanteDTO dto);

    @Mapping(source = "fechaNacimiento", target = "fecha_nacimiento", qualifiedByName = "conversorFechaString")
    ParticipanteDTO toDTO(Participante entity);

    List<Participante> toEntity(List<ParticipanteDTO> dto);

    List<ParticipanteDTO> toDTO(List<Participante> entity);

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
