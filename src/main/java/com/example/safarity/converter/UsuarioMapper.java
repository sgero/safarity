package com.example.safarity.converter;

import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario entity);

    List<UsuarioDTO> toDTO(List<Usuario> listEntity);

    List<Usuario> toEntity(List<UsuarioDTO> listDTOs);
}
