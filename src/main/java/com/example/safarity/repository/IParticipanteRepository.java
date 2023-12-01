package com.example.safarity.repository;

import com.example.safarity.model.Participante;
import com.example.safarity.model.Usuario;
import com.example.safarity.service.ParticipanteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParticipanteRepository extends JpaRepository<Participante, Integer> {

    Participante findTopByUsuario(Usuario usuario);

}
