package com.example.safarity.dto;

import com.example.safarity.model.enums.Rol;
import lombok.Data;
import lombok.NonNull;

@Data
public class UsuarioDTO {


    private Integer id;

    private String alias;

    private String password;

    private Rol rol;

}
