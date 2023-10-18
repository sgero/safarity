package com.example.safarity.service;

import com.example.safarity.converter.UsuarioMapper;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    //Listar
    public List<UsuarioDTO> listar(){
        return usuarioMapper.toDTO(usuarioRepository.findAll());
    }

    public UsuarioDTO crear(UsuarioDTO usuarioDTO){
        Usuario usuarioGuardar = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioGuardada = usuarioRepository.save(usuarioGuardar);
        UsuarioDTO usuarioGuardadaDTO = usuarioMapper.toDTO(usuarioGuardada);
        return usuarioGuardadaDTO;
    }

    public Usuario getById(Integer id){return usuarioRepository.findById(id).orElse(null);
    }

    //public void eliminar(Integer id){usuarioRepository.deleteById(id);}

}
