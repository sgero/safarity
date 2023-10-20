package com.example.safarity.converter;

import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.model.Participante;
import org.mapstruct.Mapper;
import java.util.List;


@Mapper(componentModel = "spring")


public interface ParticipanteMapper {


    Participante toEntity(ParticipanteDTO dto);

    ParticipanteDTO toDTO(Participante entity);

    List<Participante> toEntity(List<ParticipanteDTO> dto);

    List<ParticipanteDTO> toDTO(List<Participante> entity);



}
