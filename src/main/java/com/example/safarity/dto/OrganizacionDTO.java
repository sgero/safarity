package com.example.safarity.dto;

import lombok.Data;

@Data
public class OrganizacionDTO {

    private Integer id;

    private String nombre;

    private String email;

    private String cif;

    private String telefono;

    private String fechaFundacion;

    private String direccion;

    private String info;

    private String sitioWeb;

    private String logo;

    private UsuarioDTO usuarioDTO;
}
