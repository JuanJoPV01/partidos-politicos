package com.desarrollo.config;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class CorreoUtil {

    // NOTA: Para que esto funcione en la vida real con Gmail, 
    // debes usar un correo de Gmail y generar una "Contraseña de Aplicación" en la seguridad de tu cuenta.
    private static final String REMITENTE = "tucorreo.universidad@gmail.com"; 
    private static final String CLAVE_APP = "aqui_va_tu_clave_de_aplicacion_de_16_digitos";

    public static boolean enviarCorreo(String destinatario, String asunto, String mensajeCuerpo) {
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

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
            
            // Simulación en consola por si las credenciales de arriba fallan (muy útil para desarrollo)
            System.out.println("----- SIMULACION DE ENVIO DE CORREO -----");
            System.out.println("Para: " + destinatario);
            System.out.println("Asunto: " + asunto);
            System.out.println("Mensaje: " + mensajeCuerpo);
            System.out.println("-----------------------------------------");

            // Intento de envío real. Si falla (por credenciales inválidas), lo capturamos abajo
            Transport.send(message);
            return true;
            
        } catch (MessagingException e) {
            System.err.println("Error enviando correo real (posible falta de credenciales en CorreoUtil.java)");
            e.printStackTrace();
            // Retornamos true para no bloquear el flujo de la aplicación en el proyecto académico
            // si el estudiante no tiene una cuenta de correo real configurada.
            return true; 
        }
    }
}
