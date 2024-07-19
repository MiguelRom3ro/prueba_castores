<%--
  Created by IntelliJ IDEA.
  User: Mig_r
  Date: 18/07/2024
  Time: 11:07 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mensajes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .alert-center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height to center vertically */
        }
    </style>
</head>
<body>
<div class="container alert-center">
    <div>
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
        } else {
        %>
        <div class="alert alert-danger text-center">
            <span>No hay mensajes.</span>
        </div>
        <% } %>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

