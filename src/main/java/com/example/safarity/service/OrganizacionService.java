package com.example.safarity.service;

import com.example.safarity.converter.OrganizacionMapper;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Participante;
import com.example.safarity.repository.IOrganizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrganizacionService {

    @Autowired
    private IOrganizacionRepository organizacionRepository;

    @Autowired
    private OrganizacionMapper organizacionMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<OrganizacionDTO> listarOrganizacion() {
        return organizacionMapper.toDTO(organizacionRepository.findAll());
    }

    public OrganizacionDTO crearOrganizacion(OrganizacionDTO organizacionDTO) {
        return organizacionMapper.toDTO(organizacionRepository.save(organizacionMapper.toEntity(organizacionDTO)));
    }

    public Organizacion getById(Integer id) {
        return organizacionRepository.findById(id).orElse(null);
    }

    public Organizacion modificarOrganizacion(OrganizacionDTO organizacionDTO) {
        Organizacion organizacion = organizacionRepository.findById(organizacionDTO.getId()).orElse(null);

        if (organizacion == null) {
            return null;
        } else {
            organizacion.setNombre(organizacionDTO.getNombre());
            organizacion.setEmail(organizacionDTO.getEmail());
            organizacion.setTelefono(organizacionDTO.getTelefono());
            organizacion.setInfo(organizacionDTO.getInfo());
            organizacion.setSitioWeb(organizacionDTO.getSitioWeb());
            organizacion.setLogo(organizacionDTO.getLogo());

            Organizacion organizacionModificado = organizacionRepository.save(organizacion);
            return organizacionModificado;

        }
    }

    public Organizacion eliminarOrganizacion(OrganizacionDTO organizacionDTO) {
        Organizacion organizacionEliminar = organizacionRepository.findById(organizacionDTO.getId()).orElse(null);
        if (organizacionEliminar != null) {
            organizacionEliminar.setActivo(false);
            organizacionEliminar.getUsuario().setActivo(false);
            Set<Evento> eventosRelacionados = organizacionEliminar.getEventos();
            for (Evento e : eventosRelacionados) {
                e.setActivo(false);
            }
            Organizacion organizacionEliminada = organizacionRepository.save(organizacionEliminar);
            return organizacionEliminada;

        } else {
            return null;
        }
    }
//        public String eliminarProducto(ProductoDTO productoDTO){
//            Producto productoEliminar = productoRepository.findById(productoDTO.getId()).orElse(null);
//            if (productoEliminar != null) {
//                productoRepository.delete(productoEliminar);
//                return "Datos eliminados correctamente";
//            } else {
//                return "No se ha podido eliminar su producto";
//            }
//        }


    public List<OrganizacionDTO> listarLogicoOrganizacionFalse() {
        List<Organizacion> organizacionActiva = new ArrayList<>();
        for (Organizacion o : organizacionRepository.findAll()) {
            if (!o.isActivo()) {
                organizacionActiva.add(o);
            }
        }
        return organizacionMapper.toDTO(organizacionActiva);
    }

    public List<OrganizacionDTO> listarLogicoOrganizacionTrue() {
        List<Organizacion> organizacionActiva = new ArrayList<>();
        for (Organizacion o : organizacionRepository.findAll()) {
            if (o.isActivo()) {
                organizacionActiva.add(o);
            }
        }
        return organizacionMapper.toDTO(organizacionActiva);
    }

    public Organizacion save(OrganizacionDTO dto) {
        Organizacion entity = organizacionMapper.toEntity(dto);
        entity.getUsuario().setPassword(passwordEncoder.encode(entity.getUsuario().getPassword()));
        return organizacionRepository.save(entity);
    }
}
