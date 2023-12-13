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

    private Integer id_evento;

    private Integer id_usuario;
}
