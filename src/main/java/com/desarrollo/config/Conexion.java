package com.desarrollo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://localhost:5432/desarrollo_web_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Futurelo#2007";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: Driver de PostgreSQL no encontrado", e);
        }
    }
}