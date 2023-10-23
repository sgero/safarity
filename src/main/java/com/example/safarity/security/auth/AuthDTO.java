package com.example.safarity.security.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDTO {

    private String token;
    private String info;
}