package com.example.safarity.converter;

import com.example.safarity.dto.AsistenteDTO;
import com.example.safarity.model.Asistente;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AsistenteMapper {

    Asistente toEntity(AsistenteDTO dto);
    AsistenteDTO toDTO(Asistente entity);
    List<Asistente> toEntity(List<AsistenteDTO> entitys);
    List<AsistenteDTO> toDTO(List<Asistente> dtos);

}
