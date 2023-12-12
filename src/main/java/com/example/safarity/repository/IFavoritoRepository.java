package com.example.safarity.repository;

import com.example.safarity.model.Evento;
import com.example.safarity.model.Favorito;
import com.example.safarity.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IFavoritoRepository extends JpaRepository<Favorito, Long> {

    Optional<Favorito> findByParticipanteIdAndEventoId(Long participanteId, Long eventoId);

    @Query("SELECT f.evento FROM Favorito f WHERE f.participante = :participante")
    List<Evento> findEventosByParticipante(@Param("participante") Participante participante);

    @Query("SELECT f.evento FROM Favorito f WHERE f.participante = :participante AND f.evento = :evento")
    Optional<Evento> findEventoByParticipante(@Param("participante") Participante participante, @Param("evento") Evento evento);

    Optional<Favorito> findTopByEventoAndAndParticipante(Evento evento, Participante participante);

}






