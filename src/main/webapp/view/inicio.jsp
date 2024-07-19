<%@ page import="java.util.List" %>
<%@ page import="com.example.prueba_castores.models.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Mig_r
  Date: 18/07/2024
  Time: 06:32 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usr = (Usuario) session.getAttribute("usuario");
    if(usr == null) {
%>
<script>
    window.location.href = "${pageContext.request.contextPath}/login.jsp";
</script>
<% } %>
<%
    String location = request.getParameter("location");
    if (location == null || location.isEmpty()) {
        location = "inventario"; // Asume "inventario.jsp" como la página predeterminada
    }
%>

<%
    if(request.getAttribute("productos") == null && location.equalsIgnoreCase("inventario")) {
%>
<script>
    window.onload = function () {
        window.location.href = "${pageContext.request.contextPath}/ProductoController-Servlet?accion=listar";
    }
</script>
<%
        return;
    }
%>

<%
    if(request.getAttribute("productos") == null && location.equalsIgnoreCase("salidas")) {
%>
<script>
    window.onload = function () {
        window.location.href = "${pageContext.request.contextPath}/ProductoController-Servlet?accion=listarActivos";
    }
</script>
<%
        return;
    }
%>

<%
    if(request.getAttribute("historialList") == null && location.equalsIgnoreCase("historial")) {
%>
<script>
    window.onload = function () {
        window.location.href = "${pageContext.request.contextPath}/HistorialController-Servlet?accion=listarHistorial";
    }
</script>
<%
        return;
    }
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Castores Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="../css/home.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/view/nav.jsp" />
<br>
<%
    request.getSession();
    List<String> messages = (List<String>) session.getAttribute("messages");
    if (messages != null && !messages.isEmpty()) {
        for (String message : messages) {
%>
<div class="alert alert-danger text-center">
    <span><%= message %></span>
</div>
<%
        }
        // Clear messages after displaying them
        session.removeAttribute("messages");
    }
%>
<%
    if(location.equalsIgnoreCase("inventario")) {
%>
<jsp:include page="/view/inventario.jsp" />
<%
} else if(location.equalsIgnoreCase("salidas")) {
%>
<jsp:include page="/view/salidas.jsp" />
<%
} else if(location.equalsIgnoreCase("historial")) {
%>
<jsp:include page="/view/historial.jsp" />
<%
    }
%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

