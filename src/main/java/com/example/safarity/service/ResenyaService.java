package com.example.safarity.service;


import com.example.safarity.converter.AsistenteMapper;
import com.example.safarity.dto.EventoDTO;
import com.example.safarity.model.Evento;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.IAsistenteRepository;
import com.example.safarity.repository.IEventoRepository;
import com.example.safarity.repository.IUsuarioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import com.example.safarity.converter.ResenyaMapper;
import com.example.safarity.dto.ResenyaDTO;
import com.example.safarity.model.Resenya;
import com.example.safarity.repository.IResenyaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ResenyaService {

    @Autowired
    private IResenyaRepository resenyaRepository;

    @Autowired
    private ResenyaMapper resenyaMapper;

    @Autowired
    private IEventoRepository eventoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;


        public Resenya getById(Integer id) {
        return resenyaRepository.findById(id).orElse(null);
    }

    public List<ResenyaDTO> listarResenyaAll() {

        List<ResenyaDTO> listResenyas = new ArrayList<>();
        resenyaRepository.findAll().forEach(a -> listResenyas.add(resenyaMapper.toDTO(a)));

        return listResenyas;

    }


    public List<ResenyaDTO> listarResenyaSegunEvento(Long id_evento) {
         Optional<Evento> evento = eventoRepository.findById(id_evento);
         return resenyaMapper.toDTO(resenyaRepository.findAllByEvento(evento.orElse(null)));
    }

    public ResenyaDTO crearResenya(ResenyaDTO resenyaDTO) {

        return resenyaMapper.toDTO(resenyaRepository.save(resenyaMapper.toEntity(resenyaDTO)));

    }


    public Resenya modificarResenya(ResenyaDTO resenyaDTO) {
        Resenya resenya = resenyaRepository.findById(resenyaDTO.getId()).orElse(null);

        if (resenya == null) {
            return null;
        } else {
            resenya.setEstrella(resenyaDTO.getEstrella());
            resenya.setTexto(resenyaDTO.getTexto());


            Resenya resenyaModificado = resenyaRepository.save(resenya);

            return resenyaModificado;
        }

    }


    public String eliminarResenya(ResenyaDTO resenyaDTO) {
        Resenya resenyaEliminar = resenyaRepository.findById(resenyaDTO.getId()).orElse(null);
        if (resenyaEliminar != null) {
            resenyaRepository.delete(resenyaEliminar);
            return "Reseña eliminada correctamente";
        } else {

            return "No se ha podido eliminar la Reseña";
        }
    }

    public ResenyaDTO comprobarResenyaSegunEvento(ResenyaDTO resenyaDTO) {

        Optional<Evento> evento = eventoRepository.findTopById(resenyaDTO.getEventoDTO().getId());

        Optional<Usuario> usuario = usuarioRepository.findTopByAlias(resenyaDTO.getTexto());

        return resenyaMapper.toDTO(resenyaRepository.findTopByEventoAndUsuario(evento.orElse(null), usuario.orElse(null)));
    }

}
