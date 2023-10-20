package com.example.safarity.controller;

import com.example.safarity.dto.AsistenteDTO;
import com.example.safarity.model.Asistente;
import com.example.safarity.service.AsistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/asistentes")
public class AsistenteController {

    @Autowired
    private AsistenteService asistenteService;


@GetMapping(value = "/listar")
public List<AsistenteDTO> listarAsistente(){
    return asistenteService.listarAsistente();
}


@GetMapping(value = "/crear")
public AsistenteDTO crearAsistente(@RequestBody AsistenteDTO asistenteDTO){
    return asistenteService.crearAsistente(asistenteDTO);
}


@GetMapping(value = "/modificar")
public Asistente modificarAsistente(@RequestBody AsistenteDTO asistenteDTO){
    return asistenteService.modificarAsistente(asistenteDTO);
}


@GetMapping(value = "/eliminar")
public String eliminarAsistente(@RequestBody AsistenteDTO asistenteDTO){
    return asistenteService.eliminarAsistente(asistenteDTO);
}

}
