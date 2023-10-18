package com.example.safarity.service;

import com.example.safarity.converter.OrganizacionMapper;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.IOrganizacionRepository;
import com.example.safarity.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacionService {

    @Autowired
    private IOrganizacionRepository organizacionRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private OrganizacionMapper organizacionMapper;

    public List<OrganizacionDTO> listarOrganizacion() {
        return organizacionMapper.toDTO(organizacionRepository.findAll());
    }

    public OrganizacionDTO crearOrganizacion(OrganizacionDTO organizacionDTO){
        return organizacionMapper.toDTO(organizacionRepository.save(organizacionMapper.toEntity(organizacionDTO)));
    }

    public Organizacion getById(Integer id){
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
}
