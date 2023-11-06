package com.example.safarity.dto;

import lombok.Data;


@Data
public class TicketDTO {


    private Integer id;

    private String esDisponible;

    private Integer dineroAportado;

    private String fecha;

    private ParticipanteDTO participanteDTO;

    private EventoDTO eventoDTO;

    private AsistenteDTO asistenteDTO;

}
