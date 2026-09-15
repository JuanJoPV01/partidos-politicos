<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Recuperar Contraseña</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light d-flex align-items-center vh-100">
    <div class="container" style="max-width: 400px;">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h4 class="card-title text-center mb-4">Recuperar Contraseña</h4>

                <% String error = (String) request.getAttribute("error"); %>
                <% if (error != null) { %>
                    <div class="alert alert-danger p-2 text-center small"><%= error %></div>
                <% } %>
                
                <% String exito = (String) request.getAttribute("mensajeExito"); %>
                <% if (exito != null) { %>
                    <div class="alert alert-success p-2 text-center small"><%= exito %></div>
                <% } %>

                <form action="${pageContext.request.contextPath}/RecuperarClaveServlet" method="POST">
                    <p class="text-muted small text-center">Ingresa el correo asociado a tu cuenta y te enviaremos tu clave.</p>
                    <div class="mb-3">
                        <label class="form-label">Correo Electrónico</label>
                        <input type="email" name="email" class="form-control" required placeholder="tucorreo@ejemplo.com">
                    </div>
                    <button type="submit" class="btn btn-warning w-100">Enviar correo de recuperación</button>
                    
                    <div class="text-center mt-3">
                        <a href="${pageContext.request.contextPath}/vistas/login.jsp" class="text-decoration-none small">Volver al Inicio de Sesión</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
