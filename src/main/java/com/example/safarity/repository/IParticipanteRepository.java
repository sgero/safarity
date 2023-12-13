package com.example.safarity.repository;

import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Participante;
import com.example.safarity.model.Usuario;
import com.example.safarity.service.ParticipanteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IParticipanteRepository extends JpaRepository<Participante, Integer> {

    Participante findTopByUsuario(Usuario usuario);

    Participante getByUsuarioEqualsAndActivoTrue(Usuario usuario);

    @Query(value = "select o.* from {h-schema}participante o join {h-schema}usuario u on o.id_usuario = u.id where o.dni = :dni or u.alias = :alias or o.email = :email", nativeQuery = true)
    Participante orgCifAlias(String dni, String alias, String email);
}
