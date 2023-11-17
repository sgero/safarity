package com.example.safarity.repository;

import com.example.safarity.model.Evento;
import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Long> {
    // Métodos personalizados
//    @Query(value = "select e from evento e where e.nombre like %:nombre% order by e.nombre", nativeQuery = true)
//    List<Evento> obtenerEventos(String nombre);

    //List<Evento> findAllByNombreEqualsOrderByNombre(String nombre);
    List<Evento> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    List<Evento> findAllByTipoEventoEqualsAndActivoTrueOrderByNombre(TipoEvento tipoEvento);

    List<Evento> findAllByTipoPagoEqualsAndActivoTrueOrderByNombre(TipoPago tipoPago);

//    List<Evento> findAllByFechaInicioMonthEqualsAndActivoTrueOrderByNombre();

    @Query(value = "select * from {h-schema} evento e where extract('MONTH' from e.fecha_inicio) = :mes and e.activo is true order by e.nombre", nativeQuery = true)
    List<Evento> obtenerEventosMes(Integer mes);



}
