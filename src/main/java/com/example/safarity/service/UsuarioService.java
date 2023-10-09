package com.example.safarity.service;

import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


}
