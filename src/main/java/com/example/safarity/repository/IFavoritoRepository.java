package com.example.safarity.repository;

import com.example.safarity.model.Evento;
import com.example.safarity.model.Favorito;
import com.example.safarity.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoritoRepository extends JpaRepository<Favorito, Long> {

//    List<Evento> findAllByParticipante(Participante participante);

    @Query(value = "select e.id_evento from {h-schema} evento_participante e where e.id_participante = :participante", nativeQuery = true)
    List<Evento> findAllByParticipante(Participante participante);

}
