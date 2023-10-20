package com.example.safarity.service;

import com.example.safarity.converter.ParticipanteMapper;
import com.example.safarity.dto.ParticipanteDTO;
import com.example.safarity.model.Organizacion;
import com.example.safarity.model.Participante;
import com.example.safarity.repository.IParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipanteService {

    @Autowired
    private IParticipanteRepository participanteRepository;

    @Autowired
    private ParticipanteMapper participanteMapper;


    public Participante getById(Integer id){
        return participanteRepository.findById(id).orElse(null);
    }

    public List<ParticipanteDTO> listarParticipantes() {
        List<ParticipanteDTO> listParticipantes = new ArrayList<>();
        participanteRepository.findAll().forEach(p->listParticipantes.add(participanteMapper.toDTO(p)));
        return listParticipantes;
    }

    public ParticipanteDTO crearParticipante(ParticipanteDTO participanteDTO) {
        return participanteMapper.toDTO(participanteRepository.save(participanteMapper.toEntity(participanteDTO)));
    }

    public Participante modificarParticipante(ParticipanteDTO participanteDTO) {
        Participante participante = participanteRepository.findById(participanteDTO.getId()).orElse(null);

        if (participante == null) {
            return null;
        }
        else
        {

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

    public String eliminarParticipante (ParticipanteDTO participanteDTO){
        Participante participanteEliminar = participanteRepository.findById(participanteDTO.getId()).orElse(null);
        if (participanteEliminar != null){
            participanteRepository.delete(participanteEliminar);
            return "Participante eliminado correctamente";
        }else {
            return "Nose ha podido eliminar el participante";
        }



    }
}
