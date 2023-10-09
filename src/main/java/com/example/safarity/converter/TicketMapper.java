package com.example.safarity.converter;

import com.example.safarity.dto.TicketDTO;
import com.example.safarity.model.Ticket;
import org.mapstruct.Mapper;

import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public interface TicketMapper {


    Ticket toEntity(TicketDTO dto);

    TicketDTO toDTO(Ticket entity);

    List<Ticket> toEntity(List<TicketDTO> dto);

    List<TicketDTO> toDTO(List<Ticket> entity);



}
