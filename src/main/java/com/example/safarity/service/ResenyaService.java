package com.example.safarity.service;


import com.example.safarity.converter.AsistenteMapper;
import com.example.safarity.repository.IAsistenteRepository;
import org.springframework.stereotype.Service;
import com.example.safarity.converter.ResenyaMapper;
import com.example.safarity.dto.ResenyaDTO;
import com.example.safarity.model.Resenya;
import com.example.safarity.repository.IResenyaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Service
public class ResenyaService {

    @Autowired
    private IResenyaRepository resenyaRepository;

    @Autowired
    private ResenyaMapper resenyaMapper;



        public Resenya getById(Integer id) {
        return resenyaRepository.findById(id).orElse(null);
    }

    public List<ResenyaDTO> listarResenya() {

        List<ResenyaDTO> listResenyas = new ArrayList<>();
        resenyaRepository.findAll().forEach(a -> listResenyas.add(resenyaMapper.toDTO(a)));

        return listResenyas;

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


}
