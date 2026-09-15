<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.desarrollo.model.PartidoPolitico" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Partidos Políticos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <span class="navbar-brand">Sistema de Partidos Políticos</span>
            <div>
                <a href="${pageContext.request.contextPath}/ReporteServlet" class="btn btn-outline-warning btn-sm me-2">Ver Reportes</a>
                <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=listar" class="btn btn-outline-info btn-sm me-2">Gestionar Usuarios</a>
                <a href="${pageContext.request.contextPath}/LoginServlet" class="btn btn-outline-light btn-sm">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <!-- Formulario de Registro -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">Nuevo Partido</div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/PartidoServlet" method="POST">
                            <input type="hidden" name="accion" value="agregar">
                            <div class="mb-2">
                                <label class="form-label small">Nombre</label>
                                <input type="text" name="nombre" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Eslogan</label>
                                <input type="text" name="eslogan" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Presidente</label>
                                <input type="text" name="presidente" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Secretario</label>
                                <input type="text" name="secretario" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Tesorero</label>
                                <input type="text" name="tesorero" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">País</label>
                                <input type="text" name="pais" class="form-control form-control-sm" required>
                            </div>

                            <div class="row g-2 mb-2">
                                <div class="col-6">
                                    <label class="form-label small">N° Presid.</label>
                                    <input type="number" name="numPresidentes" class="form-control form-control-sm" value="0" required>
                                </div>
                                <div class="col-6">
                                    <label class="form-label small">N° Gob.</label>
                                    <input type="number" name="numGobernadores" class="form-control form-control-sm" value="0" required>
                                </div>
                                <div class="col-6">
                                    <label class="form-label small">N° Alc.</label>
                                    <input type="number" name="numAlcaldes" class="form-control form-control-sm" value="0" required>
                                </div>
                                <div class="col-6">
                                    <label class="form-label small">N° Conc.</label>
                                    <input type="number" name="numConcejales" class="form-control form-control-sm" value="0" required>
                                </div>
                                <div class="col-12">
                                    <label class="form-label small">N° Congresistas</label>
                                    <input type="number" name="numCongresistas" class="form-control form-control-sm" value="0" required>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-success w-100 btn-sm mt-2">Guardar Partido</button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Listado -->
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-dark text-white">Lista de Partidos Políticos</div>
                    <div class="card-body p-0">
                        <table class="table table-striped table-hover mb-0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Presidente</th>
                                    <th>País</th>
                                    <th>Congresistas</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<PartidoPolitico> lista = (List<PartidoPolitico>) request.getAttribute("partidos");
                                    if (lista != null && !lista.isEmpty()) {
                                        for (PartidoPolitico p : lista) {
                                %>
                                <tr>
                                    <td><%= p.getId() %></td>
                                    <td><strong><%= p.getNombre() %></strong><br><small class="text-muted"><%= p.getEslogan() %></small></td>
                                    <td><%= p.getPresidente() %></td>
                                    <td><%= p.getPais() %></td>
                                    <td><%= p.getNumCongresistas() %></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/PartidoServlet?accion=editar&id=<%= p.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                                        <a href="${pageContext.request.contextPath}/PartidoServlet?accion=eliminar&id=<%= p.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de eliminar este partido?');">Eliminar</a>
                                    </td>
                                </tr>
                                <%
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="6" class="text-center text-muted">No hay partidos registrados</td>
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