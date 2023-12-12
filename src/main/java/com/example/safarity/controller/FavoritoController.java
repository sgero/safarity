package com.example.safarity.controller;

import com.example.safarity.model.Favorito;
import com.example.safarity.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarFavorito(@RequestParam Long participanteId, @RequestParam Long eventoId) {
        favoritoService.agregarFavorito(participanteId, eventoId);
        return ResponseEntity.ok("Evento agregado a favoritos");
    }

//    @GetMapping("/eventos/{participanteId}")
//    public ResponseEntity<List<Favorito>> obtenerEventosFavoritos(@PathVariable Long participanteId) {
//        List<Favorito> eventos = favoritoService.obtenerEventosFavoritos(participanteId);
//        return ResponseEntity.ok(eventos);
//    }

    @PostMapping("/resenyas")
    public ResponseEntity<String> agregarResenya(@RequestParam Long participanteId, @RequestParam Long eventoId, @RequestBody String resenya) {
        favoritoService.agregarResenya(participanteId, eventoId, resenya);
        return ResponseEntity.ok("Reseña agregada con éxito");
    }
}