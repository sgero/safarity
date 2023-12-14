package com.example.safarity.repository;


import com.example.safarity.dto.ResenyaDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Resenya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResenyaRepository extends JpaRepository<Resenya, Integer> {

    List<Resenya> findAllByEvento(Evento evento);

}
