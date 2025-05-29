package org.library.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final Dotenv dotenv = Dotenv.configure().directory("./").load();
    public static final String URL = dotenv.get("DB_URL");
    public static final String USER = dotenv.get("DB_USER");
    public static final String PASSWORD = dotenv.get("DB_PASSWORD");

    private static Connection connection;

    public static Connection initConnection() {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new RuntimeException("Variables de entorno no configuradas correctamente");
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (SQLException exception) {
            System.out.println("Error de conexión: " + exception.getMessage());
        }
        return  connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException exception) {
            System.out.println("Error al cerrar conexión: " + exception.getMessage());
        }
    }

}
