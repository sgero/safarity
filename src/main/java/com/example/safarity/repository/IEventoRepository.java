package com.example.safarity.repository;

import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Evento> findAllByOrganizacionEquals(Organizacion organizacion);

    Evento findByIdEquals(Long id);

    @Query(value = "SELECT * FROM {h-schema}evento e " +
            "WHERE (:mes IS NULL OR EXTRACT('MONTH' FROM e.fecha_inicio) = :mes) " +
            "AND (:tipoEvento IS NULL OR e.tipo_evento = :tipoEvento) " +
            "AND (:tipoPago IS NULL OR e.tipo_pago = :tipoPago) " +
            "AND (:nombre IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND e.activo IS TRUE ORDER BY e.nombre", nativeQuery = true)
    List<Evento>consultabuscador(String nombre,Integer tipoEvento,Integer tipoPago,Integer mes);

}
