package com.example.safarity.controller;

import com.example.safarity.converter.EventoMapper;
import com.example.safarity.dto.BusquedaDTO;
import com.example.safarity.dto.EventoAuxDTO;
import com.example.safarity.dto.EventoDTO;
import com.example.safarity.dto.FavoritoDTO;
import com.example.safarity.model.Organizacion;
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
    public EventoDTO crearEvento(@RequestBody EventoAuxDTO eventoAuxDTO) {
        return eventoService.crearEvento(eventoAuxDTO);
    }

    @PutMapping(value="/modificar")
    public EventoDTO modificarEvento(@RequestBody EventoAuxDTO eventoAuxDTO) {
        return eventoMapper.toDTO(eventoService.modificarEvento(eventoAuxDTO));
    }


    @PostMapping(value="/eliminar")
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


    @PostMapping(value = "/listarOrganizacion")
    public List<EventoDTO> listarOrganizacion(@RequestBody String alias){
        return eventoService.listarOrganizacion(alias);
    }

    @PostMapping(value = "/favorito")
    public void favorito(@RequestBody FavoritoDTO favoritoDTO){
        eventoService.favorito(favoritoDTO);
    }

    @PostMapping(value = "/misFavorito")
    public List<EventoDTO> misFavorito(@RequestBody FavoritoDTO favoritoDTO){
        return eventoService.misFavorito(favoritoDTO);
    }

    @PostMapping(value = "/comprobarFavorito")
    public Boolean comprobarFavorito(@RequestBody FavoritoDTO favoritoDTO){
        return eventoService.comprobarFavorito(favoritoDTO);
    }

    @PostMapping(value = "/eliminarFavorito")
    public void eliminarFavorito(@RequestBody FavoritoDTO favoritoDTO){
        eventoService.eliminarFavorito(favoritoDTO);
    }

}
