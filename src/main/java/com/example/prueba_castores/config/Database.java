package com.example.prueba_castores.config;

import java.sql.Connection;
import java.sql.DriverManager;

//Configuracion para la base de datos
public class Database {
    private final String database_name = "castores";
    private final String server = "jdbc:mysql://localhost/" + database_name;
    private final String user = "root";
    private final String password = "";

    public Connection getConnection(){
        Connection cn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(server, user, password);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return cn;
    }

}
