package com.example.safarity.controller;

import com.example.safarity.dto.EventoDTO;
import com.example.safarity.repository.IEventoRepository;
import com.example.safarity.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private IEventoRepository eventoRepository;

    @GetMapping(value = "/listar")
    public List<EventoDTO> listarEventos(){
        return eventoService.listarEventos();
    }

    @PostMapping(value = "/crear")
    public EventoDTO crearEvento(@RequestBody EventoDTO eventoDTO) {
        return eventoService.crearEvento(eventoDTO);
    }

    @PutMapping(value="/modificar")



    @DeleteMapping(value="/eliminar")



    //    @DeleteMapping(value= "/{id}")
//    public void eliminarEvento(@PathVariable Long id) {
//        eventoService.eliminarEvento(id);
//    }
}
