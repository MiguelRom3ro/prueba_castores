<%--
  Created by IntelliJ IDEA.
  User: Mig_r
  Date: 18/07/2024
  Time: 11:07 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.prueba_castores.models.Usuario" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    Usuario usr = (Usuario) session.getAttribute("usuario");
    if(usr != null) {
%>
<script>
    window.location.href = "${pageContext.request.contextPath}/view/inicio.jsp";
</script>
<% } %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
<form action="UsuarioController-Servlet">
    <h2 class="mb-4">Iniciar sesión</h2>
    <div class="mb-3">
        <label for="email" class="form-label">Correo electronico</label>
        <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Contraseña</label>
        <input type="password" name="password" class="form-control" id="password">
    </div>
    <button type="submit" name="accion" value="login" class="btn btn-primary">Iniciar sesión</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
