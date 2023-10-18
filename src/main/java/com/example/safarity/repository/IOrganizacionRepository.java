package com.example.safarity.repository;

import com.example.safarity.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizacionRepository extends JpaRepository<Organizacion,Integer> {

}
