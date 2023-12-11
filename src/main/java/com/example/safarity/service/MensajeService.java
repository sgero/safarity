//package com.example.safarity.service;
//
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MensajeService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
//        SimpleMailMessage mensaje = new SimpleMailMessage();
//        mensaje.setTo(destinatario);
//        mensaje.setFrom(defaultSender);
//        mensaje.setSubject(asunto);
//        mensaje.setText(cuerpo);
//        javaMailSender.send(mensaje);
//    }
//
//    @Value("${mail.default.sender}")
//    private String defaultSender;
//
//    public void sendEmail(String to, String subject, String body) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        try {
//            // Configuración del remitente predeterminado
//            helper.setFrom(defaultSender);
//            // Configuración del destinatario y otros detalles del correo
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(body, true);
//
//            // Envío del correo
//            javaMailSender.send(message);
//
//        } catch (MessagingException e) {
//            // Manejar la excepción, por ejemplo, registrarla
//            e.printStackTrace();
//        }
//    }
//}
//

//package com.example.safarity.service;
//
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//
//@Service
//public class MensajeService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Value("${mail.default.sender}")
//    private String defaultSender;
//
//    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
//        sendEmail(destinatario, asunto, cuerpo);
//    }
//
//    public void sendEmail(String to, String subject, String body) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        try {
//            // Configuración del remitente predeterminado
//            helper.setFrom(defaultSender);
//            // Configuración del destinatario y otros detalles del correo
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(body, true);
//
//            // Envío del correo
//            javaMailSender.send(message);
//
//        } catch (jakarta.mail.MessagingException e) {
//            // Manejar la excepción, por ejemplo, registrarla
//            e.printStackTrace();
//        }
//    }
//}

package com.example.safarity.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MensajeService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.default.sender}")
    private String defaultSender;

    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        sendEmail("safarityteam@gmail.com", asunto, cuerpo);
    }

    public void sendEmail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            // Configuración del remitente predeterminado
            helper.setFrom(defaultSender);
            // Configuración del destinatario y otros detalles del correo
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            // Envío del correo
            javaMailSender.send(message);

        } catch (jakarta.mail.MessagingException e) {
            // Manejar la excepción, por ejemplo, registrarla
            e.printStackTrace();
        }
    }
}
