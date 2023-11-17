package com.example.safarity.repository;

import com.example.safarity.model.Token;
import com.example.safarity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Integer> {

    Token findTopByUsuario(Usuario usuario);

    Token findTopByTokenEquals(String token);

}