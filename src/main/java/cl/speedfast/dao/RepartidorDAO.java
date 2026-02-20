package cl.speedfast.dao;

import cl.speedfast.bd.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepartidorDAO {

    public int obtenerIdPorNombre(String nombre) {
        int id = -1; // valor por defecto si no encuentra el repartidor
        String sql = "SELECT id FROM repartidor WHERE nombre = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
