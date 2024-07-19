<%@ page import="com.example.prueba_castores.models.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String location = request.getParameter("location");
    if (location == null || location.isEmpty()) {
        location = "inventario"; // Asume "inventario.jsp" como la página predeterminada
    }

    // Obtener el usuario de la sesión y su rol
    session = request.getSession();
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String rol = usuario != null ? usuario.getRol().getRol() : ""; // Asegúrate de que el método getRol() existe en tu clase Usuario
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo">
        </a>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link <%= "inventario".equals(location) ? "active" : "" %>" href="${pageContext.request.contextPath}/view/inicio.jsp?location=inventario">Inventario</a>
                </li>
                <% if(!"Administrador".equals(rol)){ %>
                <li class="nav-item">
                    <a class="nav-link <%= "salidas".equals(location) ? "active" : "" %>" href="${pageContext.request.contextPath}/view/inicio.jsp?location=salidas">Sacar producto</a>
                </li>
                <% } %>
                <% if (!"Almacenista".equals(rol)) { %>
                <li class="nav-item">
                    <a class="nav-link <%= "historial".equals(location) ? "active" : "" %>" href="${pageContext.request.contextPath}/view/inicio.jsp?location=historial">Historial</a>
                </li>
                <% } %>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/UsuarioController-Servlet?accion=logout">Cerrar Sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


