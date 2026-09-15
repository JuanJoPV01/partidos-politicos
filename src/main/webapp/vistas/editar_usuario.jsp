<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.desarrollo.model.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <span class="navbar-brand">Sistema de Usuarios</span>
            <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=listar" class="btn btn-outline-light btn-sm">Volver</a>
        </div>
    </nav>

    <div class="container" style="max-width: 500px;">
        <div class="card shadow-sm">
            <div class="card-header bg-warning text-dark">Editar Usuario</div>
            <div class="card-body">
                <% Usuario u = (Usuario) request.getAttribute("usuarioEdit"); %>
                <form action="${pageContext.request.contextPath}/UsuarioServlet" method="POST">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    
                    <div class="mb-3">
                        <label class="form-label small">Nombre Completo</label>
                        <input type="text" name="nombre" class="form-control" value="<%= u.getNombre() %>" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label small">Correo Electrónico</label>
                        <input type="email" name="email" class="form-control" value="<%= u.getEmail() %>" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label small">Contraseña</label>
                        <input type="text" name="clave" class="form-control" value="<%= u.getClave() %>" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label small">Rol</label>
                        <select name="rol" class="form-select">
                            <option value="USER" <%= u.getRol().equals("USER") ? "selected" : "" %>>Usuario</option>
                            <option value="ADMIN" <%= u.getRol().equals("ADMIN") ? "selected" : "" %>>Administrador</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-warning w-100 mt-2">Guardar Cambios</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
