package com.example.safarity.converter;

import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Participante;
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
public abstract class ParticipanteMapper {
    @Autowired
    protected UsuarioService usuarioService;

    UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "usuarioDTO", target = "usuario", qualifiedByName = "conversorUsuarioEntity")
    @Mapping(source = "fecha_nacimiento", target = "fechaNacimiento", qualifiedByName = "conversorStringFecha")
    public abstract Participante toEntity(ParticipanteDTO dto);

    @Mapping(source = "usuario", target = "usuarioDTO", qualifiedByName = "conversorUsuarioDTO")
    @Mapping(source = "fechaNacimiento", target = "fecha_nacimiento", qualifiedByName = "conversorFechaString")
    public abstract ParticipanteDTO toDTO(Participante entity);

    public abstract List<Participante> toEntity(List<ParticipanteDTO> dto);

    public abstract List<ParticipanteDTO> toDTO(List<Participante> entity);

    @Named(value ="conversorFechaString")
    public String LocalDateToString(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    @Named(value ="conversorStringFecha")
    public LocalDate StringToLocalDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, formatter);
    }

    @Named(value = "conversorUsuarioEntity")
    Usuario conversor(UsuarioDTO dto){return usuarioMapper.toEntity(dto);
    }

    @Named(value = "conversorUsuarioDTO")
    UsuarioDTO conversor(Usuario entity){return usuarioMapper.toDTO(entity);
    }
}
