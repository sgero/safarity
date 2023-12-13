package com.example.safarity.repository;


import com.example.safarity.model.Resenya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResenyaRepository extends JpaRepository<Resenya, Integer> {

}
