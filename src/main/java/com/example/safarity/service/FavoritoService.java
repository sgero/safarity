package com.example.safarity.service;

import com.example.safarity.model.Evento;
import com.example.safarity.model.Favorito;
import com.example.safarity.model.Participante;
import com.example.safarity.repository.IEventoRepository;
import com.example.safarity.repository.IFavoritoRepository;
import com.example.safarity.repository.IParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FavoritoService {

        @Autowired
        private IFavoritoRepository favoritoRepository;

        @Autowired
        private IParticipanteRepository participanteRepository;

        @Autowired
        private IEventoRepository eventoRepository;

    public void agregarFavorito(Long participanteId, Long eventoId) {
        // Lógica para agregar evento a favoritos
        Participante participante = participanteRepository.findById(Math.toIntExact(participanteId))
                .orElse(null);

        Evento evento = eventoRepository.findById(eventoId)
                .orElse(null);

        Favorito favorito = new Favorito();
        favorito.setParticipante(participante);
        favorito.setEvento(evento);

        favoritoRepository.save(favorito);
    }


    @GetMapping(value = "/eventos/{participanteId}")
    public List<Favorito> obtenerEventosFavoritos(@PathVariable Long participanteId) {
        // Lógica para obtener eventos favoritos de un participante
        Participante participante = participanteRepository.findById(Math.toIntExact(participanteId))
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));

        List<Favorito> eventosFavoritos = favoritoRepository.findByParticipante(participante);
        return eventosFavoritos;
    }

    public void agregarResenya(Long participanteId, Long eventoId, String resenya) {
        // Lógica para agregar reseña a un evento
        Favorito favorito = favoritoRepository.findByParticipanteIdAndEventoId(participanteId, eventoId)
                .orElseThrow(() -> new RuntimeException("No se encontró el evento"));

        favorito.setResenya(resenya);
        favoritoRepository.save(favorito);

        // Puedes actualizar el evento en la base de datos con la nueva reseña
    }

}

