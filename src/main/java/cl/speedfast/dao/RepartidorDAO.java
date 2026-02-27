package cl.speedfast.dao;

import java.sql.*;
import cl.speedfast.bd.*;
import cl.speedfast.modelo.*;
import java.util.*;

public class RepartidorDAO {

    // Crear repartidor ( INSERT )
    public void crear(Repartidor r) { String sql = "INSERT INTO repartidor (nombre) VALUES (?)";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, r.getNombre());
            ps.executeUpdate();

            // Recuperar ID generado por MySQL
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) {
                    r.setIdRepartidor(rs.getInt(1)); // asigna el ID generado
                }
            }

            System.out.println("Repartidor registrado: " + r.getNombre());

        } catch (SQLException e) {
            System.out.println("Error al registrar repartidor");
            e.printStackTrace();
        }
    }

    // Leer todos los repartidores ( SELECT )
    public List<Repartidor> leerTodos() { List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                lista.add(new Repartidor(id, nombre, null, null));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar repartidores");
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar repartidor ( UPDATE )
    public void actualizar(Repartidor r) {
        String sql = "UPDATE repartidor SET nombre = ? WHERE id = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getNombre());
            ps.setInt(2, r.getIdRepartidor());
            ps.executeUpdate();

            System.out.println("Repartidor actualizado: " + r.getIdRepartidor());

        } catch (SQLException e) {
            System.out.println("Error al actualizar repartidor");
            e.printStackTrace();
        }
    }

    // Eliminar repartidor ( DELETE )
    public void eliminar(int id) {
        String sql = "DELETE FROM repartidor WHERE id = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Repartidor eliminado: " + id);

        } catch (SQLException e) {
            System.out.println("Error al eliminar repartidor");
            e.printStackTrace();
        }
    }

    public boolean tieneEntregas(int idRepartidor) {

        String sql = "SELECT COUNT(*) FROM entrega WHERE id_repartidor = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRepartidor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar entregas");
            e.printStackTrace();
        }

        return false;
    }

    // Obtener ID por nombre ( SELECT id )
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
            System.out.println("Error al buscar ID de repartidor");
            e.printStackTrace();
        }
        return id;
    }
}
