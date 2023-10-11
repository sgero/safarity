package com.example.safarity.service;

import com.example.safarity.converter.OrganizacionMapper;
import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.repository.IOrganizacionRepository;
import com.example.safarity.repository.IUsuarioRepository;
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
    private IUsuarioRepository usuarioRepository;

    public List<OrganizacionDTO> listarOrganizacion() {
        return organizacionMapper.toDTO(organizacionRepository.findAll());
    }

    public OrganizacionDTO crearOrganizacion(OrganizacionDTO organizacionDTO){
        return organizacionMapper.toDTO(organizacionRepository.save(organizacionMapper.toEntity(organizacionDTO)));
    }

}
