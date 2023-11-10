package com.example.safarity.dto;

import lombok.Data;


@Data
public class TicketDTO {


    private Integer id;

    private Boolean esDisponible;

    private Double dineroAportado;

    private String fecha;

    private ParticipanteDTO participanteDTO;

    private EventoDTO eventoDTO;

    private AsistenteDTO asistenteDTO;

}
