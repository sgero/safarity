package com.example.safarity.dto;

import lombok.Data;

@Data
public class ParticipanteDTO {


    private Integer id;

    private String nombre;

    private String email;

    private String apellidos;

    private String telefono;

    private String dni;

    private String fecha_nacimiento;

    private String direccion;

    private Double saldo;

    private UsuarioDTO usuarioDTO;


}
