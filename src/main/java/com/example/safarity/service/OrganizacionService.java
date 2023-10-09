package com.example.safarity.service;

import com.example.safarity.dto.OrganizacionDTO;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.repository.OrganizacionRepository;
import com.example.safarity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrganizacionService {

    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


}
