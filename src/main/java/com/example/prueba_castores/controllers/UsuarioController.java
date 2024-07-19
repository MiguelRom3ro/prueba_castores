package com.example.prueba_castores.controllers;

import com.example.prueba_castores.dao.UsuarioDAO;
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

@WebServlet(name = "UsuarioControllerServlet", urlPatterns = {"/UsuarioController-Servlet"})
public class UsuarioController extends HttpServlet {

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
                    case "login":
                        verifyLogin(request, response);
                        break;
                    case "logout":
                        logout(request, response);
                        break;
                    default:
                        response.sendRedirect("login.jsp");
                        break;
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            handleError(request, response, "Error interno: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    //Verifica si se encuentra el usuario
    private void verifyLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = getUserFromRequest(request);

        try {
            usuario = dao.login(usuario);
            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                addMessageToSession(request, "Bienvenido " + usuario.getNombre() + "!");
                response.sendRedirect("view/inicio.jsp"); // Redirige en lugar de reenviar
            } else {
                addMessageToSession(request, "Verifique sus credenciales");
                response.sendRedirect("login.jsp"); // Redirige en lugar de reenviar
            }
        } catch (SQLException e) {
            handleError(request, response, "Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            handleError(request, response, "Error interno: " + e.getMessage());
        }
    }

    //Cierra sesion
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("usuario", null);
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    //Obtiene los datos del usuario ingresados en el form
    private Usuario getUserFromRequest(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(request.getParameter("email"));
        usuario.setContrasena(request.getParameter("password"));
        return usuario;
    }

    //Manejo de errorses
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