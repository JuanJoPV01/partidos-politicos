package com.desarrollo.controller;

import com.desarrollo.dao.PartidoPoliticoDAO;
import com.desarrollo.dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReporteServlet")
public class ReporteServlet extends HttpServlet {

    private PartidoPoliticoDAO partidoDAO = new PartidoPoliticoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipo = request.getParameter("tipo");
        
        if (tipo != null) {
            switch (tipo) {
                case "pais":
                    String pais = request.getParameter("pais");
                    request.setAttribute("resultadoPartidos", partidoDAO.reportePorPais(pais));
                    request.setAttribute("filtroActivo", "Partidos por País: " + pais);
                    break;
                case "congresistas":
                    int min = Integer.parseInt(request.getParameter("min"));
                    request.setAttribute("resultadoPartidos", partidoDAO.reportePorCongresistas(min));
                    request.setAttribute("filtroActivo", "Partidos con + de " + min + " congresistas");
                    break;
                case "rol":
                    String rol = request.getParameter("rol");
                    request.setAttribute("resultadoUsuarios", usuarioDAO.reportePorRol(rol));
                    request.setAttribute("filtroActivo", "Usuarios con rol: " + rol);
                    break;
                case "busqueda":
                    String texto = request.getParameter("texto");
                    request.setAttribute("resultadoUsuarios", usuarioDAO.reporteBusqueda(texto));
                    request.setAttribute("filtroActivo", "Búsqueda de usuarios: " + texto);
                    break;
            }
        }
        
        request.getRequestDispatcher("vistas/reportes.jsp").forward(request, response);
    }
}
