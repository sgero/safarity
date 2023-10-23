package com.example.safarity.service;

import com.example.safarity.converter.UsuarioMapper;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class UsuarioService {


    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findTopByAlias(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
    }


    public Usuario buscarPorUsername(String username){
        return usuarioRepository.findTopByAlias(username).orElse(null);
    }

    public Usuario save(UsuarioDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return  usuarioRepository.save(usuarioMapper.toEntity(dto));
    }


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

    public Usuario getById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    //public void eliminar(Integer id){usuarioRepository.deleteById(id);}

    public Usuario modificarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId()).orElse(null);

        if (usuario == null) {
            return null;
        } else {
            usuario.setAlias(usuarioDTO.getAlias());
            usuario.setPassword(usuarioDTO.getPassword());

            Usuario usuarioModificado = usuarioRepository.save(usuario);
            return usuarioModificado;

        }
    }


}
