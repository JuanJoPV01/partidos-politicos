<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.desarrollo.model.PartidoPolitico" %>
<%@ page import="com.desarrollo.model.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reportes Parametrizados</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <span class="navbar-brand">Módulo de Reportes</span>
            <div>
                <a href="${pageContext.request.contextPath}/PartidoServlet?accion=listar" class="btn btn-outline-light btn-sm me-2">Volver a Partidos</a>
                <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=listar" class="btn btn-outline-light btn-sm">Volver a Usuarios</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <!-- Panel de Filtros -->
            <div class="col-md-4 mb-4">
                
                <!-- Reportes de Partidos -->
                <div class="card shadow-sm mb-3">
                    <div class="card-header bg-primary text-white">Reportes de Partidos</div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/ReporteServlet" method="GET" class="mb-3">
                            <input type="hidden" name="tipo" value="pais">
                            <label class="form-label small">1. Buscar por País</label>
                            <div class="input-group">
                                <input type="text" name="pais" class="form-control form-control-sm" required placeholder="Ej. Colombia">
                                <button class="btn btn-primary btn-sm" type="submit">Generar</button>
                            </div>
                        </form>
                        
                        <hr>
                        
                        <form action="${pageContext.request.contextPath}/ReporteServlet" method="GET">
                            <input type="hidden" name="tipo" value="congresistas">
                            <label class="form-label small">2. Mínimo de Congresistas</label>
                            <div class="input-group">
                                <input type="number" name="min" class="form-control form-control-sm" required value="10">
                                <button class="btn btn-primary btn-sm" type="submit">Generar</button>
                            </div>
                        </form>
                    </div>
                </div>
                
                <!-- Reportes de Usuarios -->
                <div class="card shadow-sm">
                    <div class="card-header bg-success text-white">Reportes de Usuarios</div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/ReporteServlet" method="GET" class="mb-3">
                            <input type="hidden" name="tipo" value="rol">
                            <label class="form-label small">1. Filtrar por Rol</label>
                            <div class="input-group">
                                <select name="rol" class="form-select form-select-sm">
                                    <option value="USER">Usuario (USER)</option>
                                    <option value="ADMIN">Administrador (ADMIN)</option>
                                </select>
                                <button class="btn btn-success btn-sm" type="submit">Generar</button>
                            </div>
                        </form>
                        
                        <hr>
                        
                        <form action="${pageContext.request.contextPath}/ReporteServlet" method="GET">
                            <input type="hidden" name="tipo" value="busqueda">
                            <label class="form-label small">2. Buscar por Nombre/Correo</label>
                            <div class="input-group">
                                <input type="text" name="texto" class="form-control form-control-sm" required placeholder="Texto a buscar...">
                                <button class="btn btn-success btn-sm" type="submit">Generar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Panel de Resultados -->
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-secondary text-white">
                        Resultados: <%= request.getAttribute("filtroActivo") != null ? request.getAttribute("filtroActivo") : "Seleccione un reporte" %>
                    </div>
                    <div class="card-body p-0">
                        
                        <% List<PartidoPolitico> resPartidos = (List<PartidoPolitico>) request.getAttribute("resultadoPartidos"); %>
                        <% if (resPartidos != null) { %>
                            <table class="table table-striped mb-0">
                                <thead class="table-primary">
                                    <tr><th>ID</th><th>Nombre</th><th>Presidente</th><th>País</th><th>Congresistas</th></tr>
                                </thead>
                                <tbody>
                                    <% if(resPartidos.isEmpty()) { %>
                                        <tr><td colspan="5" class="text-center">No se encontraron resultados</td></tr>
                                    <% } else { for(PartidoPolitico p : resPartidos) { %>
                                        <tr>
                                            <td><%= p.getId() %></td>
                                            <td><%= p.getNombre() %></td>
                                            <td><%= p.getPresidente() %></td>
                                            <td><%= p.getPais() %></td>
                                            <td><%= p.getNumCongresistas() %></td>
                                        </tr>
                                    <% } } %>
                                </tbody>
                            </table>
                        <% } %>

                        <% List<Usuario> resUsuarios = (List<Usuario>) request.getAttribute("resultadoUsuarios"); %>
                        <% if (resUsuarios != null) { %>
                            <table class="table table-striped mb-0">
                                <thead class="table-success">
                                    <tr><th>ID</th><th>Nombre</th><th>Email</th><th>Rol</th></tr>
                                </thead>
                                <tbody>
                                    <% if(resUsuarios.isEmpty()) { %>
                                        <tr><td colspan="4" class="text-center">No se encontraron resultados</td></tr>
                                    <% } else { for(Usuario u : resUsuarios) { %>
                                        <tr>
                                            <td><%= u.getId() %></td>
                                            <td><%= u.getNombre() %></td>
                                            <td><%= u.getEmail() %></td>
                                            <td><%= u.getRol() %></td>
                                        </tr>
                                    <% } } %>
                                </tbody>
                            </table>
                        <% } %>
                        
                        <% if (resPartidos == null && resUsuarios == null) { %>
                            <div class="p-5 text-center text-muted">
                                <h5>Generador de Reportes</h5>
                                <p>Utilice los formularios de la izquierda para generar un reporte.</p>
                            </div>
                        <% } %>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
