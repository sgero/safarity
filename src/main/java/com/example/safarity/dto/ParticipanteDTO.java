package com.example.safarity.dto;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;


@Data
public class ParticipanteDTO {

    @NonNull
    private Integer id;

    private String nombre;

    private String email;

    private String apellidos;

    private String telefono;

    private String dni;

    private String fechaNacimiento;

    private String direccion;

    private UsuarioDTO usuarioDTO;


}
