package com.example.safarity.dto;

import com.example.safarity.model.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrganizacionDTO {
    private Integer id;
    private String nombre;
    private String email;
    private String cif;
    private String telefono;
    private String fechaFundacion;
    private String info;
    private String sitioWeb;
    private String logo;
    private UsuarioDTO usuarioDTO;
}
