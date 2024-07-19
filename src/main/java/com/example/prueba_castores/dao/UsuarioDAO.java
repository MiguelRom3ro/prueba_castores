package com.example.prueba_castores.dao;

import com.example.prueba_castores.config.Database;
import com.example.prueba_castores.models.Usuario;
import com.example.prueba_castores.models.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends Database {

    //Inicio de sesion del usuario
    public Usuario login(Usuario usr) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT u.idUsuario, u.nombre, r.idRol, r.rol AS nombreRol FROM usuarios u "
                + "INNER JOIN roles r ON u.idRol = r.idRol "
                + "WHERE u.estatus = 1 AND u.correo = ? AND u.contrasena = ?";

        try (Connection cn = getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, usr.getCorreo());
            pst.setString(2, usr.getContrasena());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(usr.getCorreo());
                    usuario.setContrasena(usr.getContrasena());
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("idRol"));
                    rol.setRol(rs.getString("nombreRol"));
                    usuario.setRol(rol);
                    usuario.setEstatus(true);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }

        return usuario;
    }
}