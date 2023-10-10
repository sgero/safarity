package com.example.safarity.repository;
import com.example.safarity.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventoRepository extends JpaRepository<Evento, Long> {
    // Métodos personalizados


}
