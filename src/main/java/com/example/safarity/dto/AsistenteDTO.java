package com.example.safarity.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class AsistenteDTO {


    private Integer id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidos;

    @NotBlank
    @Size(max = 9, min = 9)
    private String dni;

    @NotBlank
    @Past
    private String fecha_nacimiento;

    @NotBlank
    @Digits(integer = 9, fraction = 0)
    private String telefono;

    @NotBlank
    private String direccion;

    @NotBlank
    @Email
    private String email;

    private Integer id_ticket;

}
