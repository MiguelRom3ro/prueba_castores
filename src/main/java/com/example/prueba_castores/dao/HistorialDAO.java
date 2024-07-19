package com.example.prueba_castores.dao;

import com.example.prueba_castores.models.Historial;
import com.example.prueba_castores.config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO extends Database {

    //Obtiene todos los datos del historial de la BD
    public List<Historial> getHistorial() throws SQLException {
        List<Historial> historialList = new ArrayList<>();
        String sql = "SELECT h.idMovimiento, h.idProducto, h.idUsuario, h.movimiento, h.cantidad, h.dateTim, " +
                "p.nombre AS nombreProducto, u.nombre AS nombreUsuario " +
                "FROM historial h " +
                "INNER JOIN productos p ON h.idProducto = p.idProducto " +
                "INNER JOIN usuarios u ON h.idUsuario = u.idUsuario " +
                "GROUP BY h.idMovimiento";

        try (Connection cn = getConnection();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Historial historial = new Historial();
                historial.setIdMovimiento(rs.getInt("idMovimiento"));
                historial.setIdProducto(rs.getInt("idProducto"));
                historial.setIdUsuario(rs.getInt("idUsuario"));
                historial.setMovimiento(rs.getString("movimiento"));
                historial.setCantidad(rs.getInt("cantidad"));
                historial.setDateTim(rs.getString("dateTim"));
                historial.setNombreProducto(rs.getString("nombreProducto"));
                historial.setNombreUser(rs.getString("nombreUsuario"));

                historialList.add(historial);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }

        return historialList;
    }

    //Obtiene los datos del historial de la BD aplicando un filtro
    public List<Historial> getHistorial(String filtro) throws SQLException {
        List<Historial> historialList = new ArrayList<>();
        String sql = "SELECT h.idMovimiento, h.idProducto, h.idUsuario, h.movimiento, h.cantidad, h.dateTim, " +
                "p.nombre AS nombreProducto, u.nombre AS nombreUsuario " +
                "FROM historial h " +
                "INNER JOIN productos p ON h.idProducto = p.idProducto " +
                "INNER JOIN usuarios u ON h.idUsuario = u.idUsuario " +
                "WHERE h.movimiento = ?" +
                "GROUP BY h.idMovimiento";

        try (Connection cn = getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, filtro);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Historial historial = new Historial();
                    historial.setIdMovimiento(rs.getInt("idMovimiento"));
                    historial.setIdProducto(rs.getInt("idProducto"));
                    historial.setIdUsuario(rs.getInt("idUsuario"));
                    historial.setMovimiento(rs.getString("movimiento"));
                    historial.setCantidad(rs.getInt("cantidad"));
                    historial.setDateTim(rs.getString("dateTim"));
                    historial.setNombreProducto(rs.getString("nombreProducto"));
                    historial.setNombreUser(rs.getString("nombreUsuario"));

                    historialList.add(historial);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }

        return historialList;
    }
}


