<%@ page import="com.example.prueba_castores.models.Producto" %>
<%@ page import="com.example.prueba_castores.models.Usuario" %>
<%@ page import="com.example.prueba_castores.models.Rol" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inventario de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<main class="m-5">
    <article class="mt-3">
        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            boolean isAdmin = usuario != null && "Administrador".equals(usuario.getRol().getRol());
        %>

        <% if (isAdmin) { %>
        <h2>Agregar productos</h2>
        <div class="mt-3">
            <form action="ProductoController-Servlet" method="get">
                <input type="hidden" name="accion" value="agregar">
                <label for="inputName">Nombre</label>
                <input type="text" name="nombre" id="inputName" style="width: 20rem;" required>
                <label for="inputAmount" class="ms-4">Cantidad</label>
                <input type="number" name="cantidad" id="inputAmount" style="width: 5rem;" required>
                <label for="inputStatus" class="ms-4">Estatus</label>
                <select name="status" id="inputStatus" style="width: 5rem;" required>
                    <option value="1">Activo</option>
                    <option value="0">Inactivo</option>
                </select>
                <button type="submit" class="ms-2 btn btn-primary">Agregar</button>
            </form>
        </div>
        <% } %>
    </article>
    <article class="mt-4">
        <h2>Inventario</h2>
        <table class="table table-responsive">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre del producto</th>
                <th scope="col">Cantidad</th>
                <% if (isAdmin) { %>
                <th scope="col">Ajustar Cantidad</th>
                <th scope="col">Acción</th>
                <% } %>
                <th scope="col">Estatus</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                if (productos != null) {
                    for (Producto producto : productos) {
                        boolean isActive = producto.getEstatus();
            %>
            <tr>
                <th scope="row"><%= producto.getIdProducto() %></th>
                <td><%= producto.getNombre() %></td>
                <td><%= producto.getCantidad() %></td>
                <% if (isAdmin) { %>
                <td>
                    <form action="ProductoController-Servlet" method="get" class="d-inline">
                        <input type="hidden" name="accion" value="entrada">
                        <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>">
                        <input type="number" name="cantidad" step="1" style="width: 5rem;" required>
                        <button type="submit" class="btn btn-primary">Agregar</button>
                    </form>
                </td>
                <td>
                    <% if (isActive) { %>
                    <form action="ProductoController-Servlet" method="get" class="d-inline">
                        <input type="hidden" name="accion" value="baja">
                        <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>">
                        <button type="submit" class="btn btn-danger">Dar de baja</button>
                    </form>
                    <% } else { %>
                    <form action="ProductoController-Servlet" method="get" class="d-inline">
                        <input type="hidden" name="accion" value="alta">
                        <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>">
                        <button type="submit" class="btn btn-success">Dar de alta</button>
                    </form>
                    <% } %>
                </td>
                <% } %>
                <td><%= isActive ? "Activo" : "Inactivo" %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="<%= isAdmin ? 6 : 4 %>">No hay productos en el inventario.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </article>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>


