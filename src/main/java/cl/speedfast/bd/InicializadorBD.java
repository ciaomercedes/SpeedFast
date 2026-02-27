package cl.speedfast.bd;

import java.sql.*;

public class InicializadorBD {

    public static void inicializar(){
        try (Connection con = ConexionDB.obtenerConexion();
            Statement st = con.createStatement()) {

            //Creación de la tabla repartidor
            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS repartidor (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL
                    )
                """);

            // Creación de la tabla pedido
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS pedido (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    direccion VARCHAR(150) NOT NULL,
                    tipo VARCHAR(30) NOT NULL,
                    estado VARCHAR(20) NOT NULL,
                    distancia_km DOUBLE NOT NULL,
                    nombre_repartidor VARCHAR(100)
                )
            """);

            //Creación de la tabla entrega
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS entrega (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    id_pedido INT NOT NULL,
                    id_repartidor INT NOT NULL,
                    fecha DATE NOT NULL,
                    hora TIME NOT NULL,
                    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
                    FOREIGN KEY (id_repartidor) REFERENCES repartidor(id)
                )
            """);

            //Inserción de los repartidores iniciales
            st.executeUpdate("""
                INSERT INTO repartidor (nombre)
                VALUES ('Daniel Muñoz'), ('Marcela Morales'), ('Carlos Espina')
                ON DUPLICATE KEY UPDATE nombre=nombre
            """);

            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos");
            e.printStackTrace();
        }
    }
}
