package com.example.safarity.security.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDataDTO {

    private String username;
    private String rol;
    private Long fecha_creacion;
    private Long fecha_expiracion;

}