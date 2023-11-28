package com.example.safarity.dto;

import lombok.Data;


@Data
public class TicketAuxDTO {


    private Integer id;

    private Integer dineroAportado;

    private String fecha;

    private Integer participanteDTO;

    private Long eventoDTO;

    private AsistenteDTO asistenteDTO;

}
