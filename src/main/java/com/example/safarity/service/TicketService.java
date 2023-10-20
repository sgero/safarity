package com.example.safarity.service;

import com.example.safarity.converter.TicketMapper;
import com.example.safarity.dto.TicketDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Ticket;
import com.example.safarity.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class TicketService {

    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private TicketMapper ticketMapper;


    public List<TicketDTO> listarTicket() {
        return ticketMapper.toDTO(ticketRepository.findAll());
    }

    public TicketDTO crearTicket(TicketDTO ticketDTO){

        return ticketMapper.toDTO(ticketRepository.save(ticketMapper.toEntity(ticketDTO)));

    }



//    public Ticket modificarTicket(TicketDTO ticketDTO){
//
//        return null;
//
//    }


    public Ticket eliminarTicket(TicketDTO ticketDTO){
        Ticket ticketEliminar = ticketRepository.findById(ticketDTO.getId()).orElse(null);
        if(ticketEliminar != null) {
            ticketEliminar.setActivo(false);
            ticketEliminar.getAsistente().setActivo(false);
            Ticket ticketEliminado = ticketRepository.save(ticketEliminar);
            return ticketEliminado;
        }else {
            return null;
        }

    }


}
