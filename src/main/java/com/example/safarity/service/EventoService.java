package com.example.safarity.service;

import com.example.safarity.dto.EventoDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;


    private EventoDTO convertir(Evento evento){
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setId(evento.getId());
        eventoDTO.setNombre(evento.getNombre());
        eventoDTO.setDescripcion(evento.getDescripcion());
        eventoDTO.setDireccion(evento.getDireccion());
        eventoDTO.setImagen(evento.getImagen());
        eventoDTO.setAforo(evento.getAforo());
        eventoDTO.setTotalAsistentes(evento.getTotalAsistentes());
        eventoDTO.setTipoEvento(evento.getTipoEvento());
        eventoDTO.setTipoPago(evento.getTipoPago());
        eventoDTO.setFechaInicio(evento.getFechaInicio());
        eventoDTO.setFechaFin(evento.getFechaFin());
        eventoDTO.setFechaLanzamiento(evento.getFechaLanzamiento());
        eventoDTO.setFechaVentaDisponible(evento.getFechaVentaDisponible());
        eventoDTO.setEntradasVendidas(evento.getEntradasVendidas());

        return eventoDTO;
    }


    //Obtener los eventos disponibles
    public List<EventoDTO> listarEventos() {
        List<EventoDTO> listEventos = new ArrayList<>();
        eventoRepository.findAll().forEach(e-> listEventos.add(convertir(e)));

        return listEventos;
    }

    //Eliminar un evento
    public void eliminarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

}