package com.example.safarity.service;

import com.example.safarity.repository.IOrganizacionRepository;
import com.example.safarity.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizacionService {

    @Autowired
    private IOrganizacionRepository organizacionRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;


}
