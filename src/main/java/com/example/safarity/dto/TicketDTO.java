package com.example.safarity.dto;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;


@Data
public class TicketDTO {

    @NonNull
    private Integer id;

    private String esDisponible;

    private Integer dineroAportado;

    private LocalDate fecha;

    //private ParticipanteDTO  participanteDTO;

    //private EventoDTO eventoDTO;

}
