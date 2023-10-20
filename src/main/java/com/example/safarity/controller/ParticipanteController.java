package com.example.safarity.controller;

import com.example.safarity.converter.ParticipanteMapper;
import com.example.safarity.dto.ParticipanteDTO;
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

    @Autowired
    private IParticipanteRepository participanteRepository;

    @Autowired
    private ParticipanteMapper participanteMapper;
    @GetMapping(value = "/listar")
    public List<ParticipanteDTO> listarParticipantes(){
        return participanteService.listarParticipantes();
    }

    @PostMapping(value = "/crear")
    public ParticipanteDTO crearParticipante(@RequestBody ParticipanteDTO participanteDTO) {
        return participanteService.crearParticipante(participanteDTO);
    }

    @PutMapping(value="/modificar")
    public ParticipanteDTO modificarEvento(@RequestBody ParticipanteDTO participanteDTO) {
        return participanteMapper.toDTO(participanteService.modificarParticipante(participanteDTO));
    }


    @DeleteMapping(value="/eliminar")
    public String eliminarEvento(@RequestBody ParticipanteDTO participanteDTO){
        return participanteService.eliminarParticipante(participanteDTO);
    }

}
