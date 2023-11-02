package com.example.safarity.repository;
import com.example.safarity.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Long> {
    // Métodos personalizados
    @Query(value = "select e from evento e where nombre like :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventos(String nombre);

    @Query(value = "select e from evento e where nombre = :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventosCarrera(String nombre);

    @Query(value = "select e from evento e where nombre = :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventosSubasta(String nombre);

    @Query(value = "select e from evento e where nombre = :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventosMercadillo(String nombre);

    @Query(value = "select e from evento e where nombre = :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventosConcierto(String nombre);

    @Query(value = "select e from evento e where nombre = :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventosRecogida(String nombre);

    @Query(value = "select e from evento e where nombre = :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventosEspectaculo(String nombre);

    @Query(value = "select e from evento e where nombre = :nombre order by nombre", nativeQuery = true)
    List<Evento> obtenerEventosOtro(String nombre);

}
