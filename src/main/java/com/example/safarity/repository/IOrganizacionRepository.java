package com.example.safarity.repository;

import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrganizacionRepository extends JpaRepository<Organizacion, Integer> {

    List<Organizacion> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    Organizacion findTopByCif(String cif);

}
