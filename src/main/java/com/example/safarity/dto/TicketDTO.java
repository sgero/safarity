package com.example.safarity.dto;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;


@Data
public class TicketDTO {


    private Integer id;

    private String esDisponible;

    private Integer dineroAportado;

    private String fecha;

    private ParticipanteDTO  participanteDTO;

    private EventoDTO eventoDTO;

    private AsistenteDTO asistenteDTO;

}
