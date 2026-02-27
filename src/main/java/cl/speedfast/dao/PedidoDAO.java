package cl.speedfast.dao;

import cl.speedfast.bd.ConexionDB;
import cl.speedfast.modelo.*;

import java.util.*;
import java.sql.*;

public class PedidoDAO {

    // Guardar pedido
    public void crear(Pedido pedido){

        String sql = "INSERT INTO pedido (direccion, tipo, distancia_km, estado, nombre_repartidor) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, pedido.getTipoPedido());
            ps.setDouble(3, pedido.getDistanciaKm());
            ps.setString(4, pedido.getEstado().name());
            ps.setString(5, pedido.getNombreRepartidor());

            ps.executeUpdate();

            // Recuperar ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    pedido.setIdPedido(idGenerado);
                }
            }

            System.out.println("Pedido guardado con ID: " + pedido.getIdPedido());

        } catch (SQLException e) {
            System.out.println("Error al insertar el pedido");
            e.printStackTrace();
        }
    }

    // Leer todos los pedidos
    public List<Pedido> leerTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                String estadoStr = rs.getString("estado");
                double distancia = rs.getDouble("distancia_km");
                String nombreRepartidor = rs.getString("nombre_repartidor");

                Pedido pedido = null;
                switch (tipo.toUpperCase()) {
                    case "COMIDA":
                        pedido = new PedidoComida(id, direccion, distancia);
                        break;
                    case "ENCOMIENDA":
                        pedido = new PedidoEncomienda(id, direccion, distancia);
                        break;
                    case "EXPRESS":
                        pedido = new PedidoExpress(id, direccion, distancia);
                        break;
                }

                if (pedido != null) {
                    pedido.setEstado(EstadoPedido.valueOf(estadoStr));
                    pedido.setNombreRepartidor(nombreRepartidor);
                    lista.add(pedido);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al leer pedidos");
            e.printStackTrace();
        }

        return lista;
    }

    // Actualizar pedido completo
    public void actualizar(Pedido pedido) {
        String sql = "UPDATE pedido SET direccion=?, tipo=?, estado=?, distancia_km=?, nombre_repartidor=? WHERE id=?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, pedido.getTipoPedido());
            ps.setString(3, pedido.getEstado().name());
            ps.setDouble(4, pedido.getDistanciaKm());
            ps.setString(5, pedido.getNombreRepartidor());
            ps.setInt(6, pedido.getIdPedido());

            ps.executeUpdate();
            System.out.println("Pedido actualizado: " + pedido.getIdPedido());

        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido");
            e.printStackTrace();
        }
    }

    // Eliminar pedido
    public void eliminar(Pedido pedido) {
        String sql = "DELETE FROM pedido WHERE id=?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getIdPedido());
            ps.executeUpdate();
            System.out.println("Pedido con ID " + pedido.getIdPedido() + " eliminado de la BD");

        } catch (SQLException e) {
            System.out.println("Error al intentar eliminar pedido");
            System.out.println("No se puede eliminar un pedido que se encuentra 'Entregado'");
            e.printStackTrace();
        }
    }

    public List<Pedido> listarTodos() {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedido";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                double distancia = rs.getDouble("distancia_km");
                String estadoStr = rs.getString("estado");
                String nombreRepartidor = rs.getString("nombre_repartidor");

                Pedido pedido = null;

                switch (tipo.toUpperCase()) {

                    case "COMIDA":
                        pedido = new PedidoComida(id, direccion, distancia);
                        break;

                    case "ENCOMIENDA":
                        pedido = new PedidoEncomienda(id, direccion, distancia);
                        break;

                    case "COMPRA EXPRESS":
                    case "EXPRESS":
                        pedido = new PedidoExpress(id, direccion, distancia);
                        break;
                }

                if (pedido != null) {
                    pedido.setNombreRepartidor(nombreRepartidor);
                    pedido.setEstado(EstadoPedido.valueOf(estadoStr));
                    lista.add(pedido);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos");
            e.printStackTrace();
        }

        return lista;
    }

    //Actualizar solo estado y repartidor
    public void actualizarEstado(Pedido pedido) {

        String sql = "UPDATE pedido SET estado = ?, nombre_repartidor = ? WHERE id = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getEstado().name());
            ps.setString(2, pedido.getNombreRepartidor());
            ps.setInt(3, pedido.getIdPedido());

            ps.executeUpdate();

            System.out.println("Estado del pedido actualizado en BD");

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado del pedido");
            e.printStackTrace();
        }
    }
}
