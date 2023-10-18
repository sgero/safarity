package com.example.safarity.controller;

import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.service.OrganizacionService;
import com.example.safarity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/organizacion")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;

    @GetMapping(value = "/listar")
    public List<OrganizacionDTO> listarOrganizacion(){
        return organizacionService.listarOrganizacion();
    }
    @PostMapping(value = "/crear")
    public OrganizacionDTO crearOrganizacion(@RequestBody OrganizacionDTO dto){
        return organizacionService.crearOrganizacion(dto);
    }
    @PutMapping(value = "/modificar")
    public Organizacion modificarOrganizacion(@RequestBody OrganizacionDTO organizacionDTO){
        return organizacionService.modificarOrganizacion(organizacionDTO);
    }

}
