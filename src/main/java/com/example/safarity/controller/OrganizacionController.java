package com.example.safarity.controller;

import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/organizacion")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;

    @GetMapping(value = "/listar")
    public List<OrganizacionDTO> listarOrganizacion() {
        return organizacionService.listarOrganizacion();
    }

    @PostMapping(value = "/crear")
    public OrganizacionDTO crearOrganizacion(@RequestBody OrganizacionDTO dto) {
        return organizacionService.crearOrganizacion(dto);
    }

    @PutMapping(value = "/modificar")
    public Organizacion modificarOrganizacion(@RequestBody OrganizacionDTO organizacionDTO) {
        return organizacionService.modificarOrganizacion(organizacionDTO);
    }

    //@DeleteMapping
//    public String eliminarProducto(@RequestBody ProductoDTO productoDTO){
//        return productoService.eliminarProducto(productoDTO);
//    }
    @PutMapping(value = "/eliminar")
    public OrganizacionDTO eliminarOrganizacion(@RequestBody OrganizacionDTO organizacionDTO) {
        return organizacionService.eliminarOrganizacion(organizacionDTO);
    }

    @GetMapping(value = "/listarlogicoinactivo")
    public List<OrganizacionDTO> listarLogicoOrganizacionFalse() {
        return organizacionService.listarLogicoOrganizacionFalse();
    }

    @GetMapping(value = "/listarlogicoactivo")
    public List<OrganizacionDTO> listarLogicoOrganizacionTrue() {
        return organizacionService.listarLogicoOrganizacionTrue();
    }

}
