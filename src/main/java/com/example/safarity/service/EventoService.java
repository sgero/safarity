package com.example.safarity.service;

import com.example.safarity.converter.EventoMapper;
import com.example.safarity.converter.OrganizacionMapper;
import com.example.safarity.converter.UsuarioMapper;
import com.example.safarity.dto.*;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Ticket;
import com.example.safarity.model.Token;
import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import com.example.safarity.repository.IEventoRepository;
import com.example.safarity.repository.IOrganizacionRepository;
import com.example.safarity.repository.ITokenRepository;
import com.example.safarity.repository.IUsuarioRepository;
import com.example.safarity.security.auth.TokenDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private IEventoRepository iEventoRepository;

    @Autowired
    private IOrganizacionRepository iOrganizacionRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private OrganizacionMapper organizacionMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private ITokenRepository iTokenRepository;


    public Evento getById(Long id){
        return eventoRepository.findById(id).orElse(null);
    }

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
    public EventoDTO crearEvento(EventoAuxDTO eventoAuxDTO) {
        Token toke = iTokenRepository.findTopByTokenEquals(eventoAuxDTO.getOrganizacion());
        OrganizacionDTO organizacionDTO = organizacionMapper.toDTO(iOrganizacionRepository.findTopByUsuario(toke.getUsuario()));
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setNombre(eventoAuxDTO.getNombre());
        eventoDTO.setDescripcion(eventoAuxDTO.getDescripcion());
        eventoDTO.setDireccion(eventoAuxDTO.getDireccion());
        eventoDTO.setImagen(eventoAuxDTO.getImagen());
        if (eventoAuxDTO.getTipoPago().equals("PRECIO_FIJO")) {
            eventoDTO.setPrecio(eventoAuxDTO.getPrecio());
        }else {
            eventoDTO.setPrecio(0.00);
        }
        eventoDTO.setAforo(eventoAuxDTO.getAforo());
        eventoDTO.setFecha_lanzamiento(eventoAuxDTO.getFecha_lanzamiento());
        eventoDTO.setFecha_venta(eventoAuxDTO.getFecha_venta());
        eventoDTO.setFecha_inicio(eventoAuxDTO.getFecha_inicio());
        eventoDTO.setFecha_fin(eventoAuxDTO.getFecha_fin());
        eventoDTO.setTipoEvento(TipoEvento.valueOf(eventoAuxDTO.getTipoEvento()));
        eventoDTO.setTipoPago(TipoPago.valueOf(eventoAuxDTO.getTipoPago()));


        eventoDTO.setOrganizacionDTO(organizacionDTO);


        return eventoMapper.toDTO(eventoRepository.save(eventoMapper.toEntity(eventoDTO)));
    }

    //Modificar un EVENTO
    public Evento modificarEvento(EventoAuxDTO eventoAuxDTO) {
        Evento evento = eventoRepository.findById(eventoAuxDTO.getId()).orElse(null);

        if (evento == null) {
            return null;
        }
        else
        {

        evento.setNombre(eventoAuxDTO.getNombre());
        evento.setDescripcion(eventoAuxDTO.getDescripcion());
        evento.setDireccion(eventoAuxDTO.getDireccion());
        evento.setImagen(eventoAuxDTO.getImagen());
        evento.setAforo(eventoAuxDTO.getAforo());
            if (eventoAuxDTO.getTipoPago().equals("PRECIO_FIJO")) {
                evento.setPrecio(eventoAuxDTO.getPrecio());
            }else {
                evento.setPrecio(0.00);
            }
//        evento.setTotalAsistentes(eventoDTO.getTotalAsistentes());
        evento.setTipoEvento(TipoEvento.valueOf(eventoAuxDTO.getTipoEvento()));
        evento.setTipoPago(TipoPago.valueOf(eventoAuxDTO.getTipoPago()));
        evento.setFechaInicio(eventoMapper.StringToLocalDate(eventoAuxDTO.getFecha_inicio()));
        evento.setFechaFin(eventoMapper.StringToLocalDate(eventoAuxDTO.getFecha_fin()));
        evento.setFechaLanzamiento(eventoMapper.StringToLocalDate(eventoAuxDTO.getFecha_lanzamiento()));
        evento.setFechaVentaDisponible(eventoMapper.StringToLocalDate(eventoAuxDTO.getFecha_venta()));
//        evento.setEntradasVendidas(eventoDTO.getEntradasVendidas());

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
            return "No se ha podido eliminar el evento";
        }



    }

    public String eventoEliminar(EventoDTO eventoDTO){
        Evento eventoEliminar = eventoRepository.findById(eventoDTO.getId()).orElse(null);
        if(eventoEliminar != null){
            eventoEliminar.setActivo(false);
            Set<Ticket> ticketsRelacionados = eventoEliminar.getTickets();
            for (Ticket t : ticketsRelacionados){
                t.setActivo(false);
                t.getAsistente().setActivo(false);
            }
            eventoRepository.save(eventoEliminar);
            return "Evento eliminado correctamente";

        }else{
            return "No se pudo eliminar el evento";
        }
    }

    public List<EventoDTO> listarLogicoEventoFalse() {
        List<Evento> eventoActivo = new ArrayList<>();
        for (Evento e : eventoRepository.findAll()){
            if (!e.isActivo()){
                eventoActivo.add(e);
            }
        }
        return eventoMapper.toDTO(eventoActivo);
    }
    public List<EventoDTO> listarLogicoEventoTrue() {
        List<Evento> eventoActivo = new ArrayList<>();
        for (Evento e : eventoRepository.findAll()){
            if (e.isActivo()){
                eventoActivo.add(e);
            }
        }
        return eventoMapper.toDTO(eventoActivo);
    }

    public  List<EventoDTO> busquedaEvento(BusquedaDTO busquedaDTO){
        List<Evento> eventosBuscados = iEventoRepository.findAll();

        if (!busquedaDTO.getBusqueda().isEmpty()){
           eventosBuscados.retainAll(iEventoRepository.findByNombreContainingIgnoreCaseAndActivoTrue(busquedaDTO.getBusqueda()));
//            for (Evento e : eventoRepository.findAll()){
//
//            }
        }
        if (!busquedaDTO.getTipoEvento().isEmpty()){
            eventosBuscados.retainAll(iEventoRepository.findAllByTipoEventoEqualsAndActivoTrueOrderByNombre(TipoEvento.valueOf(busquedaDTO.getTipoEvento())));
        }
        if (!busquedaDTO.getTipoPago().isEmpty()) {
            eventosBuscados.retainAll(iEventoRepository.findAllByTipoPagoEqualsAndActivoTrueOrderByNombre(TipoPago.valueOf(busquedaDTO.getTipoPago())));
        }
        if (busquedaDTO.getFecha() != 0) {
            eventosBuscados.retainAll(iEventoRepository.obtenerEventosMes(busquedaDTO.getFecha()));
        }
        return eventoMapper.toDTO(eventosBuscados);
    }



    //Método para mostrar entradas vendidas y entradas disponibles
    public EventoDTO mostrarEntradasVendidas(Long id) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento != null) {
            EventoDTO eventoDTO = eventoMapper.toDTO(evento);
            eventoDTO.setEntradasVendidas(evento.getTickets().size());
            eventoDTO.setEntradasDisponibles(evento.getAforo() - evento.getTickets().size());
            return eventoDTO;
        } else {
            return null;
        }
    }


    //Cálculo de entradas vendidas y disponibles a partir de ID que nos llegue por front al pulsar un evento.
    public EventoDTO mostrarCalculado(EventoDTO eventoFront){
        Evento eventoCalcular = eventoRepository.getById(eventoFront.getId());
        EventoDTO eventoCalculado = eventoMapper.toDTO(eventoCalcular);
        eventoCalculado.setEntradasVendidas(0);
        eventoCalculado.setEntradasDisponibles(eventoCalculado.getAforo());
        eventoCalculado.setTotalRecaudado(0.00);
        for (Ticket t : eventoCalcular.getTickets()){
            if (t.isActivo()) {
                eventoCalculado.setTotalRecaudado(eventoCalculado.getTotalRecaudado() + t.getDineroAportado());
                eventoCalculado.setEntradasVendidas(eventoCalculado.getEntradasVendidas() + 1);
                eventoCalculado.setEntradasDisponibles(eventoCalculado.getEntradasDisponibles() - 1);
            }
        }
        return eventoCalculado;
    }

    public EventoDTO eventoDetalles(Long id){
        Evento eventoCalcular = eventoRepository.getById(id);
        EventoDTO eventoCalculado = eventoMapper.toDTO(eventoCalcular);
        eventoCalculado.setEntradasVendidas(0);
        eventoCalculado.setEntradasDisponibles(eventoCalculado.getAforo());
        eventoCalculado.setTotalRecaudado(0.00);
        for (Ticket t : eventoCalcular.getTickets()){

            eventoCalculado.setTotalRecaudado(eventoCalculado.getTotalRecaudado()+t.getDineroAportado());
            eventoCalculado.setEntradasVendidas(eventoCalculado.getEntradasVendidas()+1);
            eventoCalculado.setEntradasDisponibles(eventoCalculado.getEntradasDisponibles()-1);
        }
        return eventoCalculado;
    }


}