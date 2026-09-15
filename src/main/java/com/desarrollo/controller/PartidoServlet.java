package com.desarrollo.controller;

import com.desarrollo.dao.PartidoPoliticoDAO;
import com.desarrollo.model.PartidoPolitico;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PartidoServlet")
public class PartidoServlet extends HttpServlet {

    private PartidoPoliticoDAO partidoDAO = new PartidoPoliticoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                List<PartidoPolitico> lista = partidoDAO.listar();
                request.setAttribute("partidos", lista);
                request.getRequestDispatcher("vistas/partidos.jsp").forward(request, response);
                break;
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                PartidoPolitico p = partidoDAO.obtenerPorId(idEditar);
                request.setAttribute("partido", p);
                request.getRequestDispatcher("vistas/editar_partido.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                partidoDAO.eliminar(idEliminar);
                response.sendRedirect("PartidoServlet?accion=listar");
                break;
            default:
                response.sendRedirect("PartidoServlet?accion=listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        PartidoPolitico p = new PartidoPolitico();
        p.setNombre(request.getParameter("nombre"));
        p.setEslogan(request.getParameter("eslogan"));
        p.setPresidente(request.getParameter("presidente"));
        p.setSecretario(request.getParameter("secretario"));
        p.setTesorero(request.getParameter("tesorero"));
        p.setPais(request.getParameter("pais"));
        p.setNumPresidentes(Integer.parseInt(request.getParameter("numPresidentes")));
        p.setNumGobernadores(Integer.parseInt(request.getParameter("numGobernadores")));
        p.setNumAlcaldes(Integer.parseInt(request.getParameter("numAlcaldes")));
        p.setNumConcejales(Integer.parseInt(request.getParameter("numConcejales")));
        p.setNumCongresistas(Integer.parseInt(request.getParameter("numCongresistas")));

        if ("actualizar".equals(accion)) {
            p.setId(Integer.parseInt(request.getParameter("id")));
            partidoDAO.actualizar(p);
        } else {
            partidoDAO.agregar(p);
        }
        
        response.sendRedirect("PartidoServlet?accion=listar");
    }
}