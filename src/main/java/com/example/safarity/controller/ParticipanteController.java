package com.example.safarity.controller;


import com.example.safarity.converter.UsuarioMapper;
import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.model.Participante;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.IParticipanteRepository;
import com.example.safarity.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {


    @Autowired
    private ParticipanteService participanteService;

    @GetMapping(value = "/listar")
    public List<ParticipanteDTO> listarParticipantes(){
        return participanteService.listarParticipantes();
    }

    @PostMapping(value = "/crear")
    public ParticipanteDTO crearParticipante(@RequestBody ParticipanteDTO participanteDTO) {
        return participanteService.crearParticipante(participanteDTO);
    }

    @PutMapping(value="/modificar")
    public Participante modificarParticipante(@RequestBody ParticipanteDTO participanteDTO) {
        return participanteService.modificarParticipante(participanteDTO);
    }


    @DeleteMapping(value="/eliminar")
    public String eliminarParticipante(@RequestBody ParticipanteDTO participanteDTO){
        return participanteService.eliminarParticipante(participanteDTO);
    }

}
