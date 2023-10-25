package com.example.safarity.security.auth;

import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Token;
import com.example.safarity.model.Usuario;
import com.example.safarity.model.enums.Rol;
import com.example.safarity.security.jwt.JWTService;
import com.example.safarity.service.TokenService;
import com.example.safarity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public AuthDTO login(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(usuarioDTO.getAlias());
        String apiKey = null;
        String mensaje;

        if (usuario != null) {
            if (usuarioService.validarPassword(usuario, usuarioDTO.getPassword())) {

                mensaje = "Usuario Logueado";

                //Usuario sin token
                if (usuario.getToken() == null) {
                    apiKey = jwtService.generateToken(usuario);
                    Token token = new Token();
                    token.setUsuario(usuario);
                    token.setToken(apiKey);
                    token.setFechaExpiracion(LocalDateTime.now().plusDays(1));
                    tokenService.save(token);

                    //Usuario con token caducado
                } else if (usuario.getToken().getFechaExpiracion().isBefore(LocalDateTime.now())) {
                    Token token = usuario.getToken();
                    apiKey = jwtService.generateToken(usuario);
                    token.setToken(apiKey);
                    token.setFechaExpiracion(LocalDateTime.now().plusDays(1));
                    tokenService.save(token);

                    //Usuario con token válido
                } else {
                    apiKey = usuario.getToken().getToken();
                }
            } else {
                mensaje = "Contraseña no válida";
            }
        } else {
            mensaje = "Usuario No encontrado";
        }

        return AuthDTO
                .builder()
                .token(apiKey)
                .info(mensaje)
                .build();
    }

    @PostMapping("/register")
    public AuthDTO register(@RequestBody UsuarioDTO usuarioDTO){

        usuarioDTO.setRol(Rol.PARTICIPANTE);
        Usuario usuarioNuevo = usuarioService.save(usuarioDTO);
        String token = jwtService.generateToken(usuarioNuevo);

        return AuthDTO
                .builder()
                .token(token)
                .info("Usuario creado correctamente")
                .build();
    }

}