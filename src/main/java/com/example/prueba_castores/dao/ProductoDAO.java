package com.example.prueba_castores.dao;

import com.example.prueba_castores.config.Database;
import com.example.prueba_castores.models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends Database {

    //Agrega productos a la BD
    public void addProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (nombre, cantidad, estatus) VALUES (?, ?, ?)";

        try (Connection cn = getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getCantidad());
            pst.setInt(3, producto.getEstatus() ? 1 : 0);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    //Incrementa la cantidad en un producto
    public void increaseQuantity(int idProducto, int cantidad, int idUser) throws SQLException {
        String setUserSql = "SET @id_user = ?";
        String updateProductSql = "UPDATE productos SET cantidad = cantidad + ? WHERE idProducto = ?";

        try (Connection cn = getConnection();
             PreparedStatement setUserPst = cn.prepareStatement(setUserSql);
             PreparedStatement updateProductPst = cn.prepareStatement(updateProductSql)) {

            setUserPst.setInt(1, idUser);
            setUserPst.executeUpdate();

            updateProductPst.setInt(1, cantidad);
            updateProductPst.setInt(2, idProducto);
            updateProductPst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    //Decrementa la cantidad en un producto
    public void decreaseQuantity(int idProducto, int cantidad, int idUser) throws SQLException {
        String setUserSql = "SET @id_user = ?";
        String updateProductSql = "UPDATE productos SET cantidad = cantidad - ? WHERE idProducto = ? AND cantidad >= ?";

        try (Connection cn = getConnection();
             PreparedStatement setUserPst = cn.prepareStatement(setUserSql);
             PreparedStatement updateProductPst = cn.prepareStatement(updateProductSql)) {

            setUserPst.setInt(1, idUser);
            setUserPst.executeUpdate();

            updateProductPst.setInt(1, cantidad);
            updateProductPst.setInt(2, idProducto);
            updateProductPst.setInt(3, cantidad);
            updateProductPst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }


    //Cambiar el estatus de un producto
    public void changeStatus(int idProducto, int newStatus) throws SQLException {
        String sql = "UPDATE productos SET estatus = ? WHERE idProducto = ?";

        try (Connection cn = getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setInt(1, newStatus);
            pst.setInt(2, idProducto);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    //Obtener todos los productos
    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection cn = getConnection();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setEstatus(rs.getInt("estatus") == 1);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }

        return productos;
    }

    //Obtener todos los productos activos
    public List<Producto> getActiveProducts() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE estatus = 1";

        try (Connection cn = getConnection();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setEstatus(true);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }

        return productos;
    }
}
