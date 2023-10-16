package com.example.safarity.converter;

import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Usuario;
import com.example.safarity.service.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrganizacionMapper {

    @Autowired
    protected UsuarioService usuarioService;

    UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "usuarioDTO", target = "usuario", qualifiedByName = "conversorUsuarioEntity")
    @Mapping(source = "fechaFundacion", target = "fechaFundacion", qualifiedByName = "conversorStringFecha")
    public abstract Organizacion toEntity(OrganizacionDTO dto);

    @Mapping(source = "usuario", target = "usuarioDTO", qualifiedByName = "conversorUsuarioDTO")
    @Mapping(source = "fechaFundacion", target = "fechaFundacion", qualifiedByName = "conversorFechaString")
    public abstract OrganizacionDTO toDTO(Organizacion entity);

    public abstract List<Organizacion> toEntity(List<OrganizacionDTO> dto);

    public abstract List<OrganizacionDTO> toDTO(List<Organizacion> entity);

    @Named(value = "conversorUsuarioEntity")
    Usuario conversor(UsuarioDTO dto){
        return usuarioService.getById(dto.getId());
    }

    @Named(value = "conversorUsuarioDTO")
    UsuarioDTO conversor(Usuario entity){
        return usuarioMapper.toDTO(entity);
    }

    @Named(value ="conversorFechaString")
    String LocalDateToString(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    @Named(value ="conversorStringFecha")
    LocalDate StringToLocalDate(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha,formatter);
    }
}
