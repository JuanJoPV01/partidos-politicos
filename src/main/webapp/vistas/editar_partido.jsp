<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.desarrollo.model.PartidoPolitico" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Partido - Sistema</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <span class="navbar-brand">Sistema de Partidos Políticos</span>
            <a href="${pageContext.request.contextPath}/PartidoServlet?accion=listar" class="btn btn-outline-light btn-sm">Volver</a>
        </div>
    </nav>

    <div class="container" style="max-width: 600px;">
        <div class="card shadow-sm">
            <div class="card-header bg-warning text-dark">Editar Partido</div>
            <div class="card-body">
                <% PartidoPolitico p = (PartidoPolitico) request.getAttribute("partido"); %>
                <form action="${pageContext.request.contextPath}/PartidoServlet" method="POST">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= p.getId() %>">
                    
                    <div class="mb-2">
                        <label class="form-label small">Nombre</label>
                        <input type="text" name="nombre" class="form-control" value="<%= p.getNombre() %>" required>
                    </div>
                    <div class="mb-2">
                        <label class="form-label small">Eslogan</label>
                        <input type="text" name="eslogan" class="form-control" value="<%= p.getEslogan() %>" required>
                    </div>
                    <div class="mb-2">
                        <label class="form-label small">Presidente</label>
                        <input type="text" name="presidente" class="form-control" value="<%= p.getPresidente() %>" required>
                    </div>
                    <div class="mb-2">
                        <label class="form-label small">Secretario</label>
                        <input type="text" name="secretario" class="form-control" value="<%= p.getSecretario() %>" required>
                    </div>
                    <div class="mb-2">
                        <label class="form-label small">Tesorero</label>
                        <input type="text" name="tesorero" class="form-control" value="<%= p.getTesorero() %>" required>
                    </div>
                    <div class="mb-2">
                        <label class="form-label small">País</label>
                        <input type="text" name="pais" class="form-control" value="<%= p.getPais() %>" required>
                    </div>

                    <div class="row g-2 mb-2">
                        <div class="col-6">
                            <label class="form-label small">N° Presidentes</label>
                            <input type="number" name="numPresidentes" class="form-control" value="<%= p.getNumPresidentes() %>" required>
                        </div>
                        <div class="col-6">
                            <label class="form-label small">N° Gobernadores</label>
                            <input type="number" name="numGobernadores" class="form-control" value="<%= p.getNumGobernadores() %>" required>
                        </div>
                        <div class="col-6">
                            <label class="form-label small">N° Alcaldes</label>
                            <input type="number" name="numAlcaldes" class="form-control" value="<%= p.getNumAlcaldes() %>" required>
                        </div>
                        <div class="col-6">
                            <label class="form-label small">N° Concejales</label>
                            <input type="number" name="numConcejales" class="form-control" value="<%= p.getNumConcejales() %>" required>
                        </div>
                        <div class="col-12">
                            <label class="form-label small">N° Congresistas</label>
                            <input type="number" name="numCongresistas" class="form-control" value="<%= p.getNumCongresistas() %>" required>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-warning w-100 mt-3">Guardar Cambios</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
