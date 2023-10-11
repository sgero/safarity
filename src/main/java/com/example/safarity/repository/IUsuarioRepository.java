package com.example.safarity.repository;

import com.example.safarity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer>{

}
