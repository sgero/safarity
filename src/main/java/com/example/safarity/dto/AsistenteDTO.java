package com.example.safarity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class AsistenteDTO {


    private Integer id;

    private String nombre;

    private String apellidos;

    private String dni;

    private String fecha_nacimiento;

    private String telefono;

    private String direccion;

    private String email;

    private Integer id_ticket;

}
