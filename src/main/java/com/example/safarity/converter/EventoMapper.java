package com.example.safarity.converter;

import com.example.safarity.dto.EventoDTO;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.service.OrganizacionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EventoMapper {

    @Autowired
    protected OrganizacionService organizacionService;

    OrganizacionMapper organizacionMapper = Mappers.getMapper(OrganizacionMapper.class);


    @Mapping(source = "fecha_lanzamiento", target = "fechaLanzamiento", qualifiedByName = "conversorStringFecha")
    @Mapping(source = "fecha_venta", target = "fechaVentaDisponible", qualifiedByName = "conversorStringFecha")
    @Mapping(source = "fecha_inicio", target = "fechaInicio", qualifiedByName = "conversorStringFecha")
    @Mapping(source = "fecha_fin", target = "fechaFin", qualifiedByName = "conversorStringFecha")
    @Mapping(source = "organizacionDTO", target = "organizacion", qualifiedByName = "conversorOrganizacionDTOaEntity")
    public abstract Evento toEntity(EventoDTO dto);

    @Mapping(source = "fechaLanzamiento", target = "fecha_lanzamiento", qualifiedByName = "conversorFechaString")
    @Mapping(source = "fechaVentaDisponible", target = "fecha_venta", qualifiedByName = "conversorFechaString")
    @Mapping(source = "fechaInicio", target = "fecha_inicio", qualifiedByName = "conversorFechaString")
    @Mapping(source = "fechaFin", target = "fecha_fin", qualifiedByName = "conversorFechaString")
    @Mapping(source = "organizacion", target = "organizacionDTO", qualifiedByName = "conversorOrganizacionaDTO")
    public abstract EventoDTO toDTO(Evento entity);

    public abstract List<EventoDTO> toEntity(List<EventoDTO> dto);

    public abstract List<EventoDTO> toDTO(List<Evento> entity);

    @Named(value = "conversorFechaString")
    public String LocalDateToString(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    @Named(value = "conversorStringFecha")
    public LocalDate StringToLocalDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, formatter);
    }

    @Named(value = "conversorOrganizacionDTOaEntity")
    Organizacion conversor(OrganizacionDTO dto) {
        return organizacionService.getById(dto.getId());
    }

    @Named(value = "conversorOrganizacionaDTO")
    OrganizacionDTO conversor(Organizacion entity) {
        return organizacionMapper.toDTO(entity);
    }

}
