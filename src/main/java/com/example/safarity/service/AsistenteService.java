package com.example.safarity.service;

import com.example.safarity.dto.AsistenteDTO;
import com.example.safarity.model.Asistente;
import com.example.safarity.repository.IAsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenteService {

    @Autowired
    private IAsistenteRepository asistenteRepository;

    /**
     * Listo todos los productos
     *
     * @return
     */
    public List<Asistente> listarAsistente(){

        return null;

    }

    public Asistente crearAsistente(AsistenteDTO asistenteDTO){

        return null;

    }


    public Asistente modificarAsistente(AsistenteDTO productoDTO){

        return null;

    }


    public String eliminarAsistente(AsistenteDTO productoDTO){

        return null;

    }


}
