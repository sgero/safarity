package com.example.safarity.converter;

import com.example.safarity.dto.TicketDTO;
import com.example.safarity.model.Ticket;
import com.example.safarity.service.AsistenteService;
import com.example.safarity.service.EventoService;
import org.mapstruct.Mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")


public abstract class TicketMapper {

    @Autowired
    protected EventoService eventoService;
    @Autowired
    protected ParticipanteService participanteService;
    @Autowired
    protected AsistenteService asistenteService;



    Ticket toEntity(TicketDTO dto);

    TicketDTO toDTO(Ticket entity);

    List<Ticket> toEntity(List<TicketDTO> dto);

    List<TicketDTO> toDTO(List<Ticket> entity);



}
