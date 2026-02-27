package cl.speedfast.dao;

import cl.speedfast.bd.ConexionDB;
import cl.speedfast.modelo.*;

import java.sql.*;
import java.util.*;

public class EntregaDAO {

    // Crear entrega
    public void crear(Pedido pedido, Repartidor repartidor) {

        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?,?,?,?)";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            //Id del pedido
            ps.setInt(1, pedido.getIdPedido());

            //Id del repartidor
            ps.setInt(2, repartidor.getIdRepartidor());

            // fecha y hora actuales
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.setTime(4, new java.sql.Time(System.currentTimeMillis()));

            ps.executeUpdate();

            System.out.println("Entrega registrada: Pedido " + pedido.getIdPedido()
                    + " repartidor " + repartidor.getNombre());

        } catch (SQLException e) {
            System.out.println("Error al registrar la entrega en la BD");
            e.printStackTrace();
        }
    }

    // Leer todas las entregas
    public List<String> leerTodos() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT e.id, p.direccion, r.nombre, e.fecha, e.hora " +
                "FROM entrega e " +
                "JOIN pedido p ON e.id_pedido = p.id " +
                "JOIN repartidor r ON e.id_repartidor = r.id";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String info = "Entrega ID: " + rs.getInt("id") +
                        " | Pedido: " + rs.getString("direccion") +
                        " | Repartidor: " + rs.getString("nombre") +
                        " | Fecha: " + rs.getDate("fecha") +
                        " | Hora: " + rs.getTime("hora");
                lista.add(info);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar entregas");
            e.printStackTrace();
        }

        return lista;
    }
}
