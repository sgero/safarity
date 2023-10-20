package com.example.safarity.converter;

import com.example.safarity.dto.*;
import com.example.safarity.model.*;
import com.example.safarity.service.AsistenteService;
import com.example.safarity.service.EventoService;
import com.example.safarity.service.ParticipanteService;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TicketMapper {

    @Autowired
    protected EventoService eventoService;
    @Autowired
    protected ParticipanteService participanteService;
    @Autowired
    protected AsistenteService asistenteService;

    EventoMapper eventoMapper = Mappers.getMapper(EventoMapper.class);
    ParticipanteMapper participanteMapper = Mappers.getMapper(ParticipanteMapper.class);
    AsistenteMapper asistenteMapper = Mappers.getMapper(AsistenteMapper.class);

    @Mapping(source = "eventoDTO", target = "evento", qualifiedByName = "conversorEventoEntity")
    @Mapping(source = "participanteDTO", target = "participante", qualifiedByName = "conversorParticipanteEntity")
    @Mapping(source = "asistenteDTO", target = "asistente", qualifiedByName = "conversorAsistenteEntity")
    @Mapping(source = "fecha", target = "fecha", qualifiedByName = "conversorStringFecha")
    public abstract Ticket toEntity(TicketDTO dto);

    @Mapping(source = "evento", target = "eventoDTO", qualifiedByName = "conversorEventoDTO")
    @Mapping(source = "participante", target = "participanteDTO", qualifiedByName = "conversorParticipanteDTO")
    @Mapping(source = "asistente", target = "asistenteDTO", qualifiedByName = "conversorAsistenteDTO")
    @Mapping(source = "fecha", target = "fecha", qualifiedByName = "conversorFechaString")
    public abstract TicketDTO toDTO(Ticket entity);

    public abstract List<Ticket> toEntity(List<TicketDTO> dto);

    public abstract List<TicketDTO> toDTO(List<Ticket> entity);

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

    @Named(value = "conversorEventoEntity")
    Evento conversor(EventoDTO dto){return eventoService.getById(dto.getId());
    }

    @Named(value = "conversorEventoDTO")
    EventoDTO conversor(Evento entity){return eventoMapper.toDTO(entity);
    }

    @Named(value = "conversorParticipanteEntity")
    Participante conversor(ParticipanteDTO dto){return participanteService.getById(dto.getId());
    }

    @Named(value = "conversorParticipanteDTO")
    ParticipanteDTO conversor(Participante entity){return participanteMapper.toDTO(entity);
    }

    @Named(value = "conversorAsistenteEntity")
    Asistente conversor(AsistenteDTO dto){return asistenteService.getById(dto.getId());
    }

    @Named(value = "conversorAsistenteDTO")
    AsistenteDTO conversor(Asistente entity){return asistenteMapper.toDTO(entity);
    }
}
