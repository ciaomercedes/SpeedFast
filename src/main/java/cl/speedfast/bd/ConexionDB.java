package cl.speedfast.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CLASE QUE ME PERMITE CONECTARME A LA BD SPEEDFAST
 */

public class ConexionDB {

    public static Connection obtenerConexion() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/speedfast_db", "root", "Miu,Bayek,10!!");
            System.out.println("Conexion establecida!");
        } catch (SQLException e) {
            System.out.printf("Error de conexión" + e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return connection;
    }
}
