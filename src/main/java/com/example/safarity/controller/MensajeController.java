package com.example.safarity.controller;

import com.example.safarity.dto.MensajeDTO;
import com.example.safarity.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MensajeController {



    @Autowired
    private MensajeService emailService; // Debes tener un servicio para enviar correos electrónicos


    @PostMapping("/enviar-mensaje")
    public ResponseEntity<String> enviarMensaje(@RequestBody MensajeDTO mensaje) {
        try {
            // Lógica para enviar el mensaje por correo electrónico
            emailService.enviarCorreo("safarityteam@gmail.com", "Nuevo mensaje de contacto", construirCuerpoMensaje(mensaje));
            return new ResponseEntity<>("Mensaje enviado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al enviar el mensaje", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String construirCuerpoMensaje(MensajeDTO mensaje) {
        // Construye el cuerpo del mensaje a partir de los datos del formulario
        return "Nombre: " + mensaje.getNombre() + "\nCorreo electrónico: " + mensaje.getEmail() + "\nMensaje: " + mensaje.getMensaje();
    }
}
