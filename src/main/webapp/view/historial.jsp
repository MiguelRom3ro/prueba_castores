<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.prueba_castores.models.Historial" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Historial</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .filter-by {
            display: flex;
            justify-content: end;
            align-items: baseline;
        }

        div p {
            font-size: large;
        }
    </style>
</head>
<body>
<main class="m-5">
    <article class="mt-4">
        <h2>Historial</h2>
        <div>
            <div class="filter-by pe-4">
                <p>Filtrar por:</p>
                <form action="HistorialController-Servlet" method="get" class="ms-3">
                    <input type="hidden" name="accion" value="filtrarHistorial">
                    <select class="custom-select" id="inputGroupSelect01" name="filtro" onchange="this.form.submit()">
                        <option value="Elegir...">Elegir...</option>
                        <option value="Entrada">Entradas</option>
                        <option value="Salida">Salidas</option>
                    </select>
                </form>
            </div>
        </div>
        <table class="table table-responsive mt-3">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre del producto</th>
                <th scope="col">Movimiento</th>
                <th scope="col">Nombre del usuario</th>
                <th scope="col">Fecha</th>
                <th scope="col">Cantidad</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Historial> historialList = (List<Historial>) request.getAttribute("historialList");
                if (historialList != null) {
                    for (Historial historial : historialList) {
            %>
            <tr>
                <th scope="row"><%= historial.getIdMovimiento() %></th>
                <td><%= historial.getNombreProducto() %></td>
                <td><%= historial.getMovimiento() %></td>
                <td><%= historial.getNombreUser() %></td>
                <td><%= historial.getDateTim() %></td>
                <td><%= historial.getCantidad() %></td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </article>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

