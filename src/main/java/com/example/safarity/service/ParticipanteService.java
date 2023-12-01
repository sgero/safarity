package com.example.safarity.service;

import com.example.safarity.converter.ParticipanteMapper;
import com.example.safarity.converter.UsuarioMapper;
import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.dto.UsuarioDTO;
import com.example.safarity.model.Participante;
import com.example.safarity.model.Token;
import com.example.safarity.model.enums.Rol;
import com.example.safarity.repository.IParticipanteRepository;
import com.example.safarity.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ParticipanteService {

    @Autowired
    private IParticipanteRepository participanteRepository;

    @Autowired
    private ParticipanteMapper participanteMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ITokenRepository iTokenRepository;



    public Participante getById(Integer id) {
        return participanteRepository.findById(id).orElse(null);
    }

    public List<ParticipanteDTO> listarParticipantes() {
//        List<ParticipanteDTO> listParticipantes = new ArrayList<>();
//        participanteRepository.findAll().forEach(p->listParticipantes.add(participanteMapper.toDTO(p)));

        List<Participante> listaParticipantes = new ArrayList<>();

        for (Participante p : participanteRepository.findAll()){

            if (p.getUsuario().getRol().equals(Rol.PARTICIPANTE)){

                listaParticipantes.add(p);

            }

        }

        return participanteMapper.toDTO(listaParticipantes);
    }

    public ParticipanteDTO crearParticipante(ParticipanteDTO participanteDTO) {
        return participanteMapper.toDTO(participanteRepository.save(participanteMapper.toEntity(participanteDTO)));
    }

    public Participante modificarParticipante(ParticipanteDTO participanteDTO) {
        Participante participante = participanteRepository.findById(participanteDTO.getId()).orElse(null);

        if (participante == null) {
            return null;
        } else {

            participante.setNombre(participanteDTO.getNombre());
            participante.setEmail(participanteDTO.getEmail());
            participante.setApellidos(participanteDTO.getApellidos());
            participante.setTelefono(participanteDTO.getTelefono());
            participante.setDni(participanteDTO.getDni());
            participante.setFechaNacimiento(participanteMapper.StringToLocalDate(participanteDTO.getFecha_nacimiento()));
            participante.setDireccion(participanteDTO.getDireccion());

            Participante participanteModificado = participanteRepository.save(participante);

            return participanteModificado;
        }
    }

    public String eliminarParticipante(ParticipanteDTO participanteDTO) {
        Participante participanteEliminar = participanteRepository.findById(participanteDTO.getId()).orElse(null);
        if (participanteEliminar != null) {
            participanteRepository.delete(participanteEliminar);
            return "Participante eliminado correctamente";
        } else {
            return "No se ha podido eliminar el participante";
        }

    }

    public Participante save(ParticipanteDTO dto) {
        Participante entity = participanteMapper.toEntity(dto);
        entity.getUsuario().setPassword(passwordEncoder.encode(entity.getUsuario().getPassword()));
        return participanteRepository.save(entity);
    }
    public String ParticipanteEliminar(ParticipanteDTO participanteDTO) {
        Participante participanteEliminar = participanteRepository.findById(participanteDTO.getId()).orElse(null);
        if (participanteEliminar != null) {
            participanteEliminar.setActivo(false);
            participanteEliminar.getUsuario().setActivo(false);
            participanteRepository.save(participanteEliminar);
            return "Se ha eliminado correctamente";

        } else {
            return "No se ha podido eliminar";
        }
    }

    public ParticipanteDTO mostrarParticipante(ParticipanteDTO participanteFront){
        Participante participanteCalcular = participanteRepository.getById(participanteFront.getId());

//        for (Ticket t : participanteCalcular.getTickets()){
//            participanteCalcular.setSaldo(participanteCalcular.getSaldo()-t.getDineroAportado());
//        }

        ParticipanteDTO participanteCalculado = participanteMapper.toDTO(participanteCalcular);
        return participanteCalculado;
    }

    public ParticipanteDTO getPorToken(String token){

        Token token1 = iTokenRepository.findTopByTokenEquals(token);
        return participanteMapper.toDTO(participanteRepository.findTopByUsuario(token1.getUsuario()));

    }

}
