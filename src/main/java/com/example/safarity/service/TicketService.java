package com.example.safarity.service;

import com.example.safarity.converter.TicketMapper;
import com.example.safarity.dto.TicketDTO;
import com.example.safarity.model.Participante;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Ticket;
import com.example.safarity.repository.IEventoRepository;
import com.example.safarity.repository.IParticipanteRepository;
import com.example.safarity.repository.ITicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private TicketMapper ticketMapper;


    public List<TicketDTO> listarTicket() {
        return ticketMapper.toDTO(ticketRepository.findAll());
    }

    @Transactional
    public TicketDTO crearTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.getAsistente().setTicket(ticket);
        return ticketMapper.toDTO(ticketRepository.save(ticket));

    }


//    public Ticket modificarTicket(TicketDTO ticketDTO){
//
//        return null;
//
//    }


    public Ticket eliminarTicket(TicketDTO ticketDTO) {
        Ticket ticketEliminar = ticketRepository.findById(ticketDTO.getId()).orElse(null);
        if (ticketEliminar != null) {
            ticketEliminar.setActivo(false);
            ticketEliminar.getAsistente().setActivo(false);
            Ticket ticketEliminado = ticketRepository.save(ticketEliminar);
            return ticketEliminado;
        } else {
            return null;
        }

    }


    //LÓGICA:
    //PASO 1: DADO EL ID_USUARIO, ID_EVENTO Y DINERO_APORTADO, HAY QUE GENERAR UN TICKET COGIENDO LOS DATOS DEL PARTICIPANTE QUE TENEMOS EN REGISTRO (ESCRIBIRLO)

    private final IParticipanteRepository participanteRepository;
    private final IEventoRepository eventoRepository;

    @Autowired
    public TicketService(ITicketRepository ticketRepository, IParticipanteRepository participanteRepository, IEventoRepository eventoRepository) {
        this.ticketRepository = ticketRepository;
        this.participanteRepository = participanteRepository;
        this.eventoRepository = eventoRepository;
    }


    public Ticket generarTicket(Integer id_participante, Long id_evento, double dinero_aportado) {
        // Obtener el usuario y el evento basado en los ID proporcionados
        Participante participante = participanteRepository.findById(id_participante)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Evento evento = eventoRepository.findById(id_evento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        // Crear un nuevo ticket
        Ticket ticket = new Ticket();
        ticket.setParticipante(participante);
        ticket.setEvento(evento);
        ticket.setDineroAportado(dinero_aportado);

        // Guardar el ticket en la base de datos
        ticket = ticketRepository.save(ticket);

        return ticket;
    }



}
