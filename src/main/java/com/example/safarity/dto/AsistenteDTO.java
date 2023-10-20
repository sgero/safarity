package com.example.safarity.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {""})
public class AsistenteDTO {


    private Integer id;

    private String nombre;

    private String apellidos;

    private String dni;

    private String fecha_nacimiento;

    private Integer telefono;

    private String direccion;

    private String email;

    private Integer id_ticket;

}
