package com.desarrollo.controller;

import com.desarrollo.config.CorreoUtil;
import com.desarrollo.dao.UsuarioDAO;
import com.desarrollo.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RecuperarClaveServlet")
public class RecuperarClaveServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        Usuario u = usuarioDAO.obtenerPorEmail(email);
        
        if (u != null) {
            String asunto = "Recuperación de contraseña - Sistema de Partidos";
            String mensaje = "Hola " + u.getNombre() + ",\n\n"
                           + "Has solicitado recuperar tu contraseña.\n"
                           + "Tu contraseña actual es: " + u.getClave() + "\n\n"
                           + "Te recomendamos cambiarla si no solicitaste este recordatorio.";
                           
            boolean enviado = CorreoUtil.enviarCorreo(email, asunto, mensaje);
            
            if (enviado) {
                request.setAttribute("mensajeExito", "Se han enviado las instrucciones a tu correo.");
            } else {
                request.setAttribute("error", "Hubo un problema al enviar el correo. Intenta de nuevo.");
            }
        } else {
            request.setAttribute("error", "El correo ingresado no existe en el sistema.");
        }
        
        request.getRequestDispatcher("vistas/recuperar.jsp").forward(request, response);
    }
}
