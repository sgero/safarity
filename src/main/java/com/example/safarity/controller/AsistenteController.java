package com.example.safarity.controller;

import com.example.safarity.converter.AsistenteMapper;
import com.example.safarity.dto.AsistenteDTO;
import com.example.safarity.service.AsistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/asistente")
public class AsistenteController {

    @Autowired
    private AsistenteService asistenteService;

    @Autowired
    private AsistenteMapper asistenteMapper;


    @GetMapping(value = "/listar")
    public List<AsistenteDTO> listarAsistente() {
        return asistenteService.listarAsistente();
    }


    @PostMapping(value = "/crear")
    public AsistenteDTO crearAsistente(@RequestBody AsistenteDTO asistenteDTO) {
        return asistenteService.crearAsistente(asistenteDTO);
    }


    @PutMapping(value = "/modificar")
    public AsistenteDTO modificarAsistente(@RequestBody AsistenteDTO asistenteDTO) {
        return asistenteMapper.toDTO(asistenteService.modificarAsistente(asistenteDTO));
    }


    @DeleteMapping(value = "/eliminar")
    public String eliminarAsistente(@RequestBody AsistenteDTO asistenteDTO) {
        return asistenteService.eliminarAsistente(asistenteDTO);
    }

}
