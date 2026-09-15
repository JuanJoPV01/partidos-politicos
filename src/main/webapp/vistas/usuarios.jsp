<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.desarrollo.model.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <span class="navbar-brand">Sistema - Administrador</span>
            <div>
                <a href="${pageContext.request.contextPath}/ReporteServlet" class="btn btn-outline-warning btn-sm me-2">Ver Reportes</a>
                <a href="${pageContext.request.contextPath}/PartidoServlet?accion=listar" class="btn btn-outline-info btn-sm me-2">Gestionar Partidos</a>
                <a href="${pageContext.request.contextPath}/LoginServlet" class="btn btn-outline-light btn-sm">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <!-- Formulario de Registro -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-header bg-success text-white">Nuevo Usuario</div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/UsuarioServlet" method="POST">
                            <input type="hidden" name="accion" value="agregar">
                            <div class="mb-3">
                                <label class="form-label small">Nombre Completo</label>
                                <input type="text" name="nombre" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Correo Electrónico</label>
                                <input type="email" name="email" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Contraseña</label>
                                <input type="password" name="clave" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Rol</label>
                                <select name="rol" class="form-select">
                                    <option value="USER">Usuario</option>
                                    <option value="ADMIN">Administrador</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-success w-100">Registrar Usuario</button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Listado -->
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-dark text-white">Lista de Usuarios del Sistema</div>
                    <div class="card-body p-0">
                        <table class="table table-striped table-hover mb-0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Email</th>
                                    <th>Rol</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Usuario> lista = (List<Usuario>) request.getAttribute("usuarios");
                                    if (lista != null && !lista.isEmpty()) {
                                        for (Usuario u : lista) {
                                %>
                                <tr>
                                    <td><%= u.getId() %></td>
                                    <td><%= u.getNombre() %></td>
                                    <td><%= u.getEmail() %></td>
                                    <td><span class="badge bg-<%= u.getRol().equals("ADMIN") ? "danger" : "secondary" %>"><%= u.getRol() %></span></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=editar&id=<%= u.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                                        <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=eliminar&id=<%= u.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar este usuario?');">Eliminar</a>
                                    </td>
                                </tr>
                                <%
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="5" class="text-center text-muted">No hay usuarios</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
