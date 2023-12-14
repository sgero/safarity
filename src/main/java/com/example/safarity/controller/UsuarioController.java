package com.example.safarity.controller;

import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.ITokenRepository;
import com.example.safarity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ITokenRepository iTokenRepository;

    @GetMapping(value = "/listar")
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listar();
    }

    @PostMapping(value = "/crear")
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO dto) {
        return usuarioService.crear(dto);
    }

    @PutMapping(value = "/modificar")
    public Usuario modificarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.modificarUsuario(usuarioDTO);
    }

    @PostMapping("/logout")
    public void logout(String token){
//      String tokenborrar = token;
        System.out.println("Solicitud de logout recibida con token: " + token);
        iTokenRepository.delete(iTokenRepository.findTopByTokenEquals(token));
    }


    @GetMapping("/obtener-rol")
    public ResponseEntity<Integer> obtenerRolDelUsuario(@RequestParam String nombreUsuario) {
        Integer rol = Integer.valueOf(usuarioService.getUserRol(nombreUsuario));

        if (rol != null) {
            return ResponseEntity.ok(rol);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Otra respuesta según tu lógica
        }
    }

    @PostMapping(value = "/mostrarUsuario")
    public UsuarioDTO mostrarParticipante(@RequestBody String alias) {
        return usuarioService.mostrarUsuario(alias);
    }


}
