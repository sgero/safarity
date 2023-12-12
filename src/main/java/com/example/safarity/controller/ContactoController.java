package com.example.safarity.controller;

import com.example.safarity.model.ContactoFormulario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // Reemplaza con la URL de tu frontend
public class ContactoController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/enviar-email")
    public String enviarEmail(@RequestBody ContactoFormulario formulario) {
        try {
            enviarCorreo(formulario);
            return "Correo enviado con éxito.";
        } catch (MessagingException | jakarta.mail.MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }

    private void enviarCorreo(ContactoFormulario formulario) throws MessagingException, jakarta.mail.MessagingException {
        jakarta.mail.internet.MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("sgarciaguerrero@safareyes.es");
        helper.setSubject("Nuevo mensaje de contacto");
        helper.setText("Nombre: " + formulario.getNombre() + "\n"
                + "Email: " + formulario.getEmail() + "\n"
                + "Mensaje: \n" + formulario.getMensaje());

        mailSender.send(message);
    }
}
