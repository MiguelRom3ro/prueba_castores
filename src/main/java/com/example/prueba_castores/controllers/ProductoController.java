package com.example.prueba_castores.controllers;

import com.example.prueba_castores.dao.ProductoDAO;
import com.example.prueba_castores.models.Producto;
import com.example.prueba_castores.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductoControllerServlet", urlPatterns = {"/ProductoController-Servlet"})
public class ProductoController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    //Manejo de acciones
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("accion");
        try {
            if (action != null) {
                switch (action) {
                    case "agregar":
                        addProducto(request, response);
                        break;
                    case "entrada":
                        adjustQuantity(request, response, true);
                        break;
                    case "salida":
                        adjustQuantity(request, response, false);
                        break;
                    case "alta":
                        changeStatus(request, response, 1);
                        break;
                    case "baja":
                        changeStatus(request, response, 0);
                        break;
                    case "listar":
                        listarProductos(request, response);
                        break;
                    case "listarActivos":
                        listarActivos(request,response);
                    default:
                        addMessageToSession(request, "Tu petición fue inválida.");
                        response.sendRedirect("mensaje.jsp");
                        break;
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            //handleError(request, response, "Error interno: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    //Agregar producto
    private void addProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAO();
            Producto producto = new Producto();
            producto.setNombre(request.getParameter("nombre"));
            producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            producto.setEstatus(Integer.parseInt(request.getParameter("status")) == 1);

            if(producto.getCantidad() < 0){
                addMessageToSession(request, "No se aceptan negativos.");
            }else{
                dao.addProducto(producto);
                addMessageToSession(request, "Producto agregado exitosamente.");
                List<Producto> productos = dao.getAllProductos();
                request.setAttribute("productos", productos);
            }
            response.sendRedirect("view/inicio.jsp?location=inventario");

        } catch (SQLException e) {
            handleError(request, response, "Error al agregar producto: " + e.getMessage());
        }
    }

    //Agregar o restar cantidad
    private void adjustQuantity(HttpServletRequest request, HttpServletResponse response, boolean increase) throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAO();
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (increase) {
                if(cantidad > 0) {
                    dao.increaseQuantity(idProducto, cantidad, usuario.getIdUsuario());
                    handleError(request, response, "Cantidad aumentada con exito");
                } else {
                    handleError(request, response, "No se puede disminuir");
                }
                List<Producto> productos = dao.getAllProductos();
                request.setAttribute("productos", productos);
                response.sendRedirect("view/inicio.jsp?location=inventario");
            } else {
                int cCantidad = Integer.parseInt(request.getParameter("cCantidad"));
                if(cantidad > cCantidad){
                    handleError(request, response, "No se puede reducir mas que la cantidad actual");
                    response.sendRedirect("view/inicio.jsp?location=salidas");
                }else if(cantidad <= 0){
                    handleError(request, response, "No se aceptan 0 o negativos");
                    response.sendRedirect("view/inicio.jsp?location=salidas");
                }else{
                    dao.decreaseQuantity(idProducto, cantidad, usuario.getIdUsuario());
                    handleError(request, response, "Cantidad reducida exitosamente.");
                    List<Producto> productos = dao.getActiveProducts();
                    request.setAttribute("productos", productos);
                    response.sendRedirect("view/inicio.jsp?location=salidas");
                }
            }

        } catch (SQLException e) {
            handleError(request, response, "Error al ajustar cantidad: " + e.getMessage());
        }
    }

    //Cambia el estatus de un producto
    private void changeStatus(HttpServletRequest request, HttpServletResponse response, int newStatus) throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAO();
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            dao.changeStatus(idProducto, newStatus);
            String statusMessage = (newStatus == 1) ? "Producto activado exitosamente." : "Producto desactivado exitosamente.";
            handleError(request,response, statusMessage);

            List<Producto> productos = dao.getAllProductos();
            request.setAttribute("productos", productos);
            response.sendRedirect("view/inicio.jsp?location=inventario");

        } catch (SQLException e) {
            handleError(request, response, "Error al cambiar estatus: " + e.getMessage());
        }
    }

    //Lista los productos en general
    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAO();
            List<Producto> productos = dao.getAllProductos();
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("view/inicio.jsp?location=inventario").forward(request, response);
        } catch (SQLException e) {
            handleError(request, response, "Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            handleError(request, response, "Error interno: " + e.getMessage());
        }
    }

    //Lista solo los productos activos
    private void listarActivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAO();
            List<Producto> productos = dao.getActiveProducts();
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("view/inicio.jsp?location=salidas").forward(request, response);
        } catch (SQLException e) {
            handleError(request, response, "Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            handleError(request, response, "Error interno: " + e.getMessage());
        }
    }


    //Manejo de errores
    private void handleError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        addMessageToSession(request, message);
    }

    //Almacena los errores para mostrarlos posteriormente
    private void addMessageToSession(HttpServletRequest request, String message) {
        HttpSession session = request.getSession();
        List<String> messages = (List<String>) session.getAttribute("messages");
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
        session.setAttribute("messages", messages);
    }
}
