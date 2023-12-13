package com.example.safarity.repository;

import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrganizacionRepository extends JpaRepository<Organizacion, Integer> {

    List<Organizacion> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    Organizacion findTopByCif(String cif);

    Organizacion findTopByUsuario(Usuario usuario);

    @Query(value = "select o.* from {h-schema}organizacion o join {h-schema}usuario u on o.id_usuario = u.id where o.cif = :cif or u.alias = :alias", nativeQuery = true)
    Organizacion orgCifAlias(String cif,String alias);

}
