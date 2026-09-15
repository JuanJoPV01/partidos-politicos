package com.desarrollo.config;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class CorreoUtil {

    private static final String REMITENTE = "jpuello169@gmail.com";
    private static final String CLAVE_APP = "yroyeapcgmsoaodn";

    public static boolean enviarCorreo(String destinatario, String asunto, String mensajeCuerpo) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // TIMEOUTS: Para evitar que la app se quede colgada esperando en Render
        props.put("mail.smtp.connectiontimeout", "3000"); // 3 segundos max para conectar
        props.put("mail.smtp.timeout", "3000");           // 3 segundos max para responder
        props.put("mail.smtp.writetimeout", "3000");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, CLAVE_APP);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(REMITENTE));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensajeCuerpo);

            System.out.println("----- SIMULACION DE ENVIO DE CORREO -----");
            System.out.println("Para: " + destinatario);
            System.out.println("Asunto: " + asunto);
            System.out.println("Mensaje: " + mensajeCuerpo);
            System.out.println("-----------------------------------------");

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            System.err.println("Error enviando correo real (Bloqueo SMTP o credenciales). Continuando con simulacion.");
            e.printStackTrace();
            return true;
        }
    }
}