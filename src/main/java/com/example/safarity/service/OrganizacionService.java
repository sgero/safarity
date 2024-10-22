package com.example.safarity.service;

import com.example.safarity.converter.EventoMapper;
import com.example.safarity.converter.OrganizacionMapper;
import com.example.safarity.dto.BusquedaDTO;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Ticket;
import com.example.safarity.model.Usuario;
import com.example.safarity.model.enums.TipoEvento;
import com.example.safarity.model.enums.TipoPago;
import com.example.safarity.repository.IEventoRepository;
import com.example.safarity.repository.IOrganizacionRepository;
import com.example.safarity.repository.IUsuarioRepository;
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

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IEventoRepository eventoRepository;

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
        }
        else
        {

            organizacion.setNombre(organizacionDTO.getNombre());
            organizacion.setEmail(organizacionDTO.getEmail());
            organizacion.setCif(organizacionDTO.getCif());
            organizacion.setTelefono(organizacionDTO.getTelefono());
            organizacion.setInfo(organizacionDTO.getInfo());
            organizacion.setSitioWeb(organizacionDTO.getSitioWeb());
            organizacion.setLogo(organizacionDTO.getLogo());
            organizacion.setDireccion(organizacionDTO.getDireccion());


            Organizacion organizacionModificado = organizacionRepository.save(organizacion);
            return organizacionModificado;

        }
    }

    public String eliminarOrganizacion(OrganizacionDTO organizacionDTO) {
        Organizacion organizacionEliminar = organizacionRepository.findById(organizacionDTO.getId()).orElse(null);
        if (organizacionEliminar != null) {
            organizacionEliminar.setActivo(false);
            organizacionEliminar.getUsuario().setActivo(false);
            Set<Evento> eventosRelacionados = organizacionEliminar.getEventos();
            for (Evento e : eventosRelacionados) {
                e.setActivo(false);
                for (Ticket t : e.getTickets()){
                    t.setActivo(false);
                    t.getAsistente().setActivo(false);
                }
            }
            organizacionRepository.save(organizacionEliminar);
            return "Se ha eliminado correctamente";

        } else {
            return "No se ha podido eliminar";
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

    public OrganizacionDTO mostrarCalculado(String alias){
        Usuario usuario = iUsuarioRepository.findAllByAliasAndActivoTrue(alias);
        Organizacion organizacionCalcular = organizacionRepository.findTopByUsuario(usuario);
        OrganizacionDTO organizacionCalculado = organizacionMapper.toDTO(organizacionCalcular);
        organizacionCalculado.setMonedero(0.00);
        for (Evento e : organizacionCalcular.getEventos()){
            for (Ticket t : e.getTickets()){
                if (t.isActivo()){
                    organizacionCalculado.setMonedero(organizacionCalculado.getMonedero()+t.getDineroAportado());
                }
            }
        }
        return organizacionCalculado;
    }
    public  List<OrganizacionDTO> busquedaOrganizacion(BusquedaDTO busquedaDTO){
        if (busquedaDTO.getBusqueda() != null){
            return organizacionMapper.toDTO(organizacionRepository.findByNombreContainingIgnoreCaseAndActivoTrue(busquedaDTO.getBusqueda()));
        }else {
            return null;
        }

    }

}
