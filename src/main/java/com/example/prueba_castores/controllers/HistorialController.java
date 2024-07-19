package com.example.prueba_castores.controllers;

import com.example.prueba_castores.dao.HistorialDAO;
import com.example.prueba_castores.models.Historial;
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

@WebServlet(name = "HistorialControllerServlet", urlPatterns = {"/HistorialController-Servlet"})
public class HistorialController extends HttpServlet {

    //Manejo de acciones
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("accion");

        try {
            if (action != null) {
                switch (action) {
                    case "listarHistorial":
                        listarHistorial(request, response);
                        break;
                    case "filtrarHistorial":
                        filtrarHistorial(request, response);
                        break;
                    default:
                        response.sendRedirect("view/historial.jsp");
                        break;
                }
            } else {
                response.sendRedirect("view/historial.jsp");
            }
        } catch (Exception e) {
            handleError(request, response, "Error interno: " + e.getMessage());
        }
    }

    //Devuelve un listado general del historial
    private void listarHistorial(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HistorialDAO dao = new HistorialDAO();
        List<Historial> historialList = dao.getHistorial();
        request.setAttribute("historialList", historialList);
        request.getRequestDispatcher("view/inicio.jsp?location=historial").forward(request, response);
    }

    //Devuelve un historial con un filtro de Entrada o Salida
    private void filtrarHistorial(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String filtro = request.getParameter("filtro");
        HistorialDAO dao = new HistorialDAO();
        List<Historial> historialList = dao.getHistorial(filtro);
        request.setAttribute("historialList", historialList);
        request.getRequestDispatcher("view/inicio.jsp?location=historial").forward(request, response);
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

