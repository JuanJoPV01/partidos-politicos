package com.desarrollo.controller;

import com.desarrollo.dao.UsuarioDAO;
import com.desarrollo.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                List<Usuario> lista = usuarioDAO.listar();
                request.setAttribute("usuarios", lista);
                request.getRequestDispatcher("vistas/usuarios.jsp").forward(request, response);
                break;
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Usuario u = usuarioDAO.obtenerPorId(idEditar);
                request.setAttribute("usuarioEdit", u);
                request.getRequestDispatcher("vistas/editar_usuario.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                usuarioDAO.eliminar(idEliminar);
                response.sendRedirect("UsuarioServlet?accion=listar");
                break;
            default:
                response.sendRedirect("UsuarioServlet?accion=listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        Usuario u = new Usuario();
        u.setNombre(request.getParameter("nombre"));
        u.setEmail(request.getParameter("email"));
        u.setClave(request.getParameter("clave"));
        u.setRol(request.getParameter("rol"));

        if ("actualizar".equals(accion)) {
            u.setId(Integer.parseInt(request.getParameter("id")));
            usuarioDAO.actualizar(u);
        } else {
            usuarioDAO.agregar(u);
        }

        response.sendRedirect("UsuarioServlet?accion=listar");
    }
}
