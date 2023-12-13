package com.example.safarity.dto;


import lombok.*;

import javax.validation.constraints.*;

@Data
public class ResenyaDTO {

    private Integer id;

    @NotBlank
    private Integer estrella;

    @NotBlank
    private String texto;

    private EventoDTO eventoDTO;

    private UsuarioDTO usuarioDTO;
}
