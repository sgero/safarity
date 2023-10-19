package com.example.safarity.converter;

import com.example.safarity.dto.EventoDTO;
import com.example.safarity.dto.TicketDTO;
import com.example.safarity.model.Evento;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EventoMapper {

    Evento toEntity(EventoDTO dto);

    EventoDTO toDTO (Evento entity);

}
