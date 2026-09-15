package com.desarrollo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:postgresql://dpg-dakq26mk1f9s73cgtk10-a.oregon-postgres.render.com:5432/desarrollo_wb_db?sslmode=require";
    private static final String USER = "desarrollo_wb_db_user";
    private static final String PASSWORD = "IvpcqhH4uaApBCBmkep7yqcnrpIyHbJv";

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}