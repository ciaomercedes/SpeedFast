package cl.speedfast.dao;

import cl.speedfast.bd.ConexionDB;
import cl.speedfast.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntregaDAO {

    public void guardar(Pedido pedido, Repartidor repartidor) {

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
}
