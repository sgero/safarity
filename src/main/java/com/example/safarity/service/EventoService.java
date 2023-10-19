package com.example.safarity.service;

import com.example.safarity.converter.EventoMapper;
import com.example.safarity.dto.EventoDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.repository.IEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private IEventoRepository eventoRepository;

//Dejo el conversor a DTO ya que lo voy a hacer con el mapper abajo (donde tengo el eventoMapper.toDTO iria el convertir)
//    private EventoDTO convertir(Evento evento){
//
//        EventoDTO eventoDTO = new EventoDTO();
//        eventoDTO.setId(evento.getId());
//        eventoDTO.setNombre(evento.getNombre());
//        eventoDTO.setDescripcion(evento.getDescripcion());
//        eventoDTO.setDireccion(evento.getDireccion());
//        eventoDTO.setImagen(evento.getImagen());
//        eventoDTO.setAforo(evento.getAforo());
//        eventoDTO.setTotalAsistentes(evento.getTotalAsistentes());
//        eventoDTO.setTipoEvento(evento.getTipoEvento());
//        eventoDTO.setTipoPago(evento.getTipoPago());
//        eventoDTO.setFechaInicio(evento.getFechaInicio());
//        eventoDTO.setFechaFin(evento.getFechaFin());
//        eventoDTO.setFechaLanzamiento(evento.getFechaLanzamiento());
//        eventoDTO.setFechaVentaDisponible(evento.getFechaVentaDisponible());
//        eventoDTO.setEntradasVendidas(evento.getEntradasVendidas());
//
//        return eventoDTO;
//    }

    @Autowired
    private EventoMapper eventoMapper;

    //Obtener los eventos disponibles
    public List<EventoDTO> listarEventos() {
        List<EventoDTO> listEventos = new ArrayList<>();
        eventoRepository.findAll().forEach(e-> listEventos.add(eventoMapper.toDTO(e)));

        return listEventos;
    }

//    //Eliminar un evento
//    public void eliminarEvento(Long id) {
//        eventoRepository.deleteById(id);
//    }

    //Método para crear y modificar que se llame GUARDAR
    //Me lo cargo porque lo voy a hacer con el MAPPER
//    public void guardarEvento(EventoDTO eventoDTO) {
//        Evento evento = new Evento();
//        evento.setId(eventoDTO.getId());
//        evento.setNombre(eventoDTO.getNombre());
//        evento.setDescripcion(eventoDTO.getDescripcion());
//        evento.setDireccion(eventoDTO.getDireccion());
//        evento.setImagen(eventoDTO.getImagen());
//        evento.setAforo(eventoDTO.getAforo());
//        evento.setTotalAsistentes(eventoDTO.getTotalAsistentes());
//        evento.setTipoEvento(eventoDTO.getTipoEvento());
//        evento.setTipoPago(eventoDTO.getTipoPago());
//        evento.setFechaInicio(eventoDTO.getFechaInicio());
//        evento.setFechaFin(eventoDTO.getFechaFin());
//        evento.setFechaLanzamiento(eventoDTO.getFechaLanzamiento());
//        evento.setFechaVentaDisponible(eventoDTO.getFechaVentaDisponible());
//        evento.setEntradasVendidas(eventoDTO.getEntradasVendidas());
//
//        eventoRepository.save(evento);
//    }


    //Crear y modificar en un mismo método????
//    public void guardarEvento(EventoDTO eventoDTO) {
//        Evento evento = eventoMapper.toEntity(eventoDTO);
//        eventoRepository.save(evento);
//
//    }


    //Crear un EVENTO
    public EventoDTO crearEvento(EventoDTO eventoDTO) {
        return eventoMapper.toDTO(eventoRepository.save(eventoMapper.toEntity(eventoDTO)));
    }

    //Modificar un EVENTO
    public Evento modificarEvento(EventoDTO eventoDTO) {
        Evento evento = eventoRepository.findById(eventoDTO.getId()).orElse(null);

        if (evento == null) {
            return null;
        }
        else
        {

        evento.setNombre(eventoDTO.getNombre());
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setDireccion(eventoDTO.getDireccion());
        evento.setImagen(eventoDTO.getImagen());
        evento.setAforo(eventoDTO.getAforo());
        evento.setTotalAsistentes(eventoDTO.getTotalAsistentes());
        evento.setTipoEvento(eventoDTO.getTipoEvento());
        evento.setTipoPago(eventoDTO.getTipoPago());
        evento.setFechaInicio(eventoDTO.getFechaInicio());
        evento.setFechaFin(eventoDTO.getFechaFin());
        evento.setFechaLanzamiento(eventoDTO.getFechaLanzamiento());
        evento.setFechaVentaDisponible(eventoDTO.getFechaVentaDisponible());
        evento.setEntradasVendidas(eventoDTO.getEntradasVendidas());

        Evento eventoModificado = eventoRepository.save(evento);

        return eventoModificado;
        }
    }

    public String eliminarEvento (EventoDTO eventoDTO){
        Evento eventoEliminar = eventoRepository.findById(eventoDTO.getId()).orElse(null);
        if (eventoEliminar != null){
            eventoRepository.delete(eventoEliminar);
            return "Evento eliminado correctamente";
            }else {
            return "Nose ha podido eliminar el evento";
        }



    }
}