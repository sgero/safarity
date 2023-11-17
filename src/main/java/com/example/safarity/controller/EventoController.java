package com.example.safarity.controller;

import com.example.safarity.converter.EventoMapper;
import com.example.safarity.dto.BusquedaDTO;
import com.example.safarity.dto.EventoDTO;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.repository.IEventoRepository;
import com.example.safarity.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private IEventoRepository eventoRepository;

    @Autowired
    private EventoMapper eventoMapper;

    @GetMapping(value = "/listar")
    public List<EventoDTO> listarEventos(){
        return eventoService.listarEventos();
    }

    @PostMapping(value = "/crear")
    public EventoDTO crearEvento(@RequestBody EventoDTO eventoDTO) {
        return eventoService.crearEvento(eventoDTO);
    }

    @PutMapping(value="/modificar")
    public EventoDTO modificarEvento(@RequestBody EventoDTO eventoDTO) {
        return eventoMapper.toDTO(eventoService.modificarEvento(eventoDTO));
    }


    @PutMapping(value="/eliminar")
    public String eliminarEvento(@RequestBody EventoDTO eventoDTO){
        return eventoService.eliminarEvento(eventoDTO);
    }

    @PutMapping(value="/eliminar-logico")
    public String eventoEliminar(@RequestBody EventoDTO eventoDTO) {
        return eventoService.eventoEliminar(eventoDTO);
    }

    @GetMapping(value = "/listarlogicoinactivo")
    public List<EventoDTO> listarLogicoEventoFalse(){
        return eventoService.listarLogicoEventoFalse();
    }
    @GetMapping(value = "/listarlogicoactivo")
    public List<EventoDTO> listarLogicoEventoTrue(){
        return eventoService.listarLogicoEventoTrue();
    }
    //    @DeleteMapping(value= "/{id}")
//    public void eliminarEvento(@PathVariable Long id) {
//        eventoService.eliminarEvento(id);
//    }

    @PostMapping(value = "/buscar")
    public List<EventoDTO> listarBusqueda(@RequestBody BusquedaDTO busquedaDTO){
        return eventoService.busquedaEvento(busquedaDTO);
    }

        @PostMapping(value = "/mostrarCalculado")
        public EventoDTO mostrarCalculado(@RequestBody EventoDTO eventoDTO) {
            return eventoService.mostrarCalculado(eventoDTO);
        }

    @GetMapping(value = "/detalles")
    public EventoDTO eventoDetalles(@RequestParam Long id) {
        return eventoService.eventoDetalles(id);
    }

}
