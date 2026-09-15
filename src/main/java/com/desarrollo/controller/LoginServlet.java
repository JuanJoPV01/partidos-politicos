package com.desarrollo.controller;

import com.desarrollo.dao.UsuarioDAO;
import com.desarrollo.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        Usuario usuario = usuarioDAO.validar(email, clave);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);
            response.sendRedirect("PartidoServlet?accion=listar");
        } else {
            request.setAttribute("error", "Credenciales incorrectas");
            request.getRequestDispatcher("vistas/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("vistas/login.jsp");
    }
}