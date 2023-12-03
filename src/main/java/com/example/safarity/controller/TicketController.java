package com.example.safarity.controller;

import com.example.safarity.dto.TicketAuxDTO;
import com.example.safarity.dto.TicketDTO;
import com.example.safarity.dto.TicketDevDTO;
import com.example.safarity.model.Ticket;
import com.example.safarity.service.PdfService;
import com.example.safarity.service.TicketService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ticket")
public class TicketController {


    private final TicketService ticketService;
    private final PdfService pdfService;

    @Autowired
    public TicketController(TicketService ticketService, PdfService pdfService) {
        this.ticketService = ticketService;
        this.pdfService = pdfService;
    }


    //@Autowired
    //private TicketService ticketService;

    @GetMapping(value = "/listar")
    public List<TicketDTO> listarTicket() {
        return ticketService.listarTicket();
    }

    @PostMapping(value = "/crear")
    public TicketDTO crearTicket(@RequestBody TicketAuxDTO dto) {
        return ticketService.crearTicket(dto);
    }
//    @PutMapping(value = "/modificar")
//    public Organizacion modificarOrganizacion(@RequestBody OrganizacionDTO organizacionDTO){
//        return organizacionService.modificarOrganizacion(organizacionDTO);
//    }

    @PutMapping(value = "/eliminar")
    public String eliminarTicket(@RequestBody TicketDevDTO ticketDevDTO) {
        return ticketService.eliminarTicket(ticketDevDTO);
    }

//    @GetMapping(value = "/listarlogicoinactivo")
//    public List<OrganizacionDTO> listarLogicoOrganizacionFalse(){
//        return organizacionService.listarLogicoOrganizacionFalse();
//    }
//    @GetMapping(value = "/listarlogicoactivo")
//    public List<OrganizacionDTO> listarLogicoOrganizacionTrue(){
//        return organizacionService.listarLogicoOrganizacionTrue();
//    }

    @PostMapping(value = "/listarPTickets")
    public List<TicketDTO> listarPorParticipante(@RequestBody TicketDevDTO ticketDevDTO) {
        return ticketService.listarTicketParticipante(ticketDevDTO);
    }

    @PostMapping(value = "/mostrarTicket")
    public TicketDTO mostrarTicket(@RequestBody TicketDTO ticketfront) {
        return ticketService.mostrarTicket(ticketfront);
    }


    @GetMapping(value = "/pdf")
    public void downloadPDF(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generateTicketPdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}