package com.example.safarity.service;

import com.example.safarity.converter.TicketMapper;
import com.example.safarity.dto.TicketDTO;
import com.example.safarity.model.Ticket;
import com.example.safarity.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private TicketMapper ticketMapper;


    public List<TicketDTO> listarTicket() {
        return ticketMapper.toDTO(ticketRepository.findAll());
    }

    public Ticket crearTicket(TicketDTO ticketDTO){

        return null;

    }


    public Ticket modificarTicket(TicketDTO productoDTO){

        return null;

    }

    @DeleteMapping(value="/eliminar")
    public String eliminarTicket(@RequestBody TicketDTO ticketDTO){
            return ticketService.eliminarTicket(ticketDTO);
        }

    }


}
