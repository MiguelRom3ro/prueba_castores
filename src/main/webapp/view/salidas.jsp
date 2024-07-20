<%@ page import="com.example.prueba_castores.models.Producto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mig_r
  Date: 18/07/2024
  Time: 06:33 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salidas de productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<main class="m-5">
    <article class="mt-4">
        <h2>Salida de productos</h2>
        <table class="table table-responsive mt-3">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre del producto</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Restar</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                if (productos != null) {
                    for (Producto producto : productos) {
            %>
            <tr>
                <th scope="row"><%=producto.getIdProducto()%></th>
                <th><%=producto.getNombre()%></th>
                <td><%=producto.getCantidad()%></td>
                <td>
                    <form action="ProductoController-Servlet" method="get" class="d-inline">
                        <input type="hidden" name="accion" value="salida">
                        <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>">
                        <input type="hidden" name="cCantidad" value="<%= producto.getCantidad() %>">
                        <input type="number" name="cantidad" step="1" style="width: 5rem;" required>
                        <button type="submit" class="btn btn-danger">Restar</button>
                    </form>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6">No hay productos en el inventario.</td>
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

