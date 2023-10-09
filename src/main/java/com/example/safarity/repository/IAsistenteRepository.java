package com.example.safarity.repository;

import com.example.safarity.model.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAsistenteRepository extends JpaRepository<Asistente,Integer> {



}
