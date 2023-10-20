package com.example.safarity.controller;

import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.dto.TicketDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Ticket;
import com.example.safarity.service.OrganizacionService;
import com.example.safarity.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "/listar")
    public List<TicketDTO> listarTicket(){
        return ticketService.listarTicket();
    }

    @PostMapping(value = "/crear")
    public TicketDTO crearTicket(@RequestBody TicketDTO dto){
        return ticketService.crearTicket(dto);
    }
//    @PutMapping(value = "/modificar")
//    public Organizacion modificarOrganizacion(@RequestBody OrganizacionDTO organizacionDTO){
//        return organizacionService.modificarOrganizacion(organizacionDTO);
//    }

    @PutMapping(value = "/eliminar")
    public Ticket eliminarTicket(@RequestBody TicketDTO ticketDTO){
        return ticketService.eliminarTicket(ticketDTO);
    }

//    @GetMapping(value = "/listarlogicoinactivo")
//    public List<OrganizacionDTO> listarLogicoOrganizacionFalse(){
//        return organizacionService.listarLogicoOrganizacionFalse();
//    }
//    @GetMapping(value = "/listarlogicoactivo")
//    public List<OrganizacionDTO> listarLogicoOrganizacionTrue(){
//        return organizacionService.listarLogicoOrganizacionTrue();
//    }
}
