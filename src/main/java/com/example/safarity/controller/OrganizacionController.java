package com.example.safarity.controller;

import com.example.safarity.converter.EventoMapper;
import com.example.safarity.converter.OrganizacionMapper;
import com.example.safarity.dto.BusquedaDTO;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/organizacion")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;

    @Autowired
    private OrganizacionMapper organizacionMapper;

    @GetMapping(value = "/listar")
    public List<OrganizacionDTO> listarOrganizacion() {
        return organizacionService.listarOrganizacion();
    }

    @PostMapping(value = "/crear")
    public OrganizacionDTO crearOrganizacion(@RequestBody OrganizacionDTO dto) {
        return organizacionService.crearOrganizacion(dto);
    }

    @PutMapping(value = "/modificar")
    public OrganizacionDTO modificarOrganizacion(@RequestBody OrganizacionDTO organizacionDTO) {
        return organizacionMapper.toDTO(organizacionService.modificarOrganizacion(organizacionDTO));
    }

    //@DeleteMapping
//    public String eliminarProducto(@RequestBody ProductoDTO productoDTO){
//        return productoService.eliminarProducto(productoDTO);
//    }
    @PutMapping(value = "/eliminar")
    public String eliminarOrganizacion(@RequestBody OrganizacionDTO organizacionDTO) {
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

    @PostMapping(value = "/mostrarcalculado")
    public OrganizacionDTO mostrarCalculado(@RequestBody String alias) {
        return organizacionService.mostrarCalculado(alias);
    }
    @PostMapping(value = "/buscar")
    public List<OrganizacionDTO> listarBusqueda(@RequestBody BusquedaDTO busquedaDTO){
        return organizacionService.busquedaOrganizacion(busquedaDTO);
    }

}
