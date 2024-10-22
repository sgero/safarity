package com.example.safarity.repository;

import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findTopByAlias(String alias);
    Optional<Usuario> findTopByPassword(String password);
    Usuario findAllByAliasAndActivoTrue(String alias);

}
