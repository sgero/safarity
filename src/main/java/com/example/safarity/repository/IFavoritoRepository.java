package com.example.safarity.repository;

import com.example.safarity.model.Favorito;
import com.example.safarity.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IFavoritoRepository extends JpaRepository<Favorito, Long> {


        List<Favorito> findByParticipante(Participante participante);


        Optional<Favorito> findByParticipanteIdAndEventoId(Long participanteId, Long eventoId);



}






