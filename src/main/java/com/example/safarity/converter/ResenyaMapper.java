package com.example.safarity.converter;


import com.example.safarity.dto.EventoDTO;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.dto.ResenyaDTO;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Resenya;
import com.example.safarity.model.Usuario;
import com.example.safarity.service.EventoService;
import com.example.safarity.service.UsuarioService;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ResenyaMapper {

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected UsuarioMapper usuarioMapper;

    @Autowired
    protected EventoService eventoService;

    @Autowired
    protected EventoMapper eventoMapper;


    @Mapping(source = "usuarioDTO", target = "usuario", qualifiedByName = "conversorUsuarioDTOaEntity")
    @Mapping(source = "eventoDTO", target = "evento", qualifiedByName = "conversorEventoDTOaEntity")
    public abstract   Resenya toEntity(ResenyaDTO dto);

    @Mapping(source = "usuario", target = "usuarioDTO", qualifiedByName = "conversorUsuarioaDTO")
    @Mapping(source = "evento", target = "eventoDTO", qualifiedByName = "conversorEventoaDTO")
    public abstract   ResenyaDTO toDTO(Resenya entity);

    public abstract  List<Resenya> toEntity(List<ResenyaDTO> dtos);

    public abstract  List<ResenyaDTO> toDTO(List<Resenya> entitys);


    @Named(value = "conversorUsuarioDTOaEntity")
    Usuario conversor(UsuarioDTO dto) {
        return usuarioService.getById(dto.getId());
    }

    @Named(value = "conversorUsuarioaDTO")
    UsuarioDTO conversor(Usuario entity) {
        return usuarioMapper.toDTO(entity);
    }

    @Named(value = "conversorEventoDTOaEntity")
    Evento conversor(EventoDTO dto) {
        return eventoService.getById(dto.getId());
    }

    @Named(value = "conversorEventoaDTO")
    EventoDTO conversor(Evento entity) {
        return eventoMapper.toDTO(entity);
    }


}
