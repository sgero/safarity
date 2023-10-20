package com.example.safarity.service;

import com.example.safarity.converter.AsistenteMapper;
import com.example.safarity.dto.AsistenteDTO;
import com.example.safarity.model.Asistente;
import com.example.safarity.repository.IAsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenteService {

    @Autowired
    private IAsistenteRepository asistenteRepository;

    @Autowired
    private AsistenteMapper asistenteMapper;

    /**
     * Listo todos los productos
     *
     * @return
     */


    public Asistente getById(Integer id) {
        return asistenteRepository.findById(id).orElse(null);
    }

    public List<AsistenteDTO> listarAsistente() {

        List<AsistenteDTO> listAsistentes = new ArrayList<>();
        asistenteRepository.findAll().forEach(a -> listAsistentes.add(asistenteMapper.toDTO(a)));

        return listAsistentes;

    }

    public AsistenteDTO crearAsistente(AsistenteDTO asistenteDTO) {

        return asistenteMapper.toDTO(asistenteRepository.save(asistenteMapper.toEntity(asistenteDTO)));

    }


    public Asistente modificarAsistente(AsistenteDTO asistenteDTO) {
        Asistente asistente = asistenteRepository.findById(asistenteDTO.getId()).orElse(null);

        if (asistente == null) {
            return null; }
        else
        {
        asistente.setNombre(asistenteDTO.getNombre());
        asistente.setApellidos(asistenteDTO.getApellidos());
        asistente.setEmail(asistenteDTO.getEmail());
        asistente.setDireccion(asistenteDTO.getDireccion());
        asistente.setDni(asistenteDTO.getDni());
        asistente.setTelefono(asistenteDTO.getTelefono());
        asistente.setFecha_nacimiento(asistenteDTO.getFecha_nacimiento());

        Asistente asistenteModificado = asistenteRepository.save(asistente);

        return asistenteModificado;
        }

    }



    public String eliminarAsistente(AsistenteDTO asistenteDTO){
        Asistente asistenteEliminar = asistenteRepository.findById(asistenteDTO.getId()).orElse(null);
        if (asistenteEliminar != null) {
            asistenteRepository.delete(asistenteEliminar);
            return "Asistente eliminado correctamente";
            }else {

            return "No se ha podido eliminar el asistente";
        }
    }


}
