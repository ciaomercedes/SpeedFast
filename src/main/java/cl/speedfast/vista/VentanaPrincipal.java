package cl.speedfast.vista;

import javax.swing.*;
import java.awt.*;
import cl.speedfast.controladores.ControladorDeEnvios;
import cl.speedfast.dao.*;
import cl.speedfast.modelo.*;

public class VentanaPrincipal extends javax.swing.JFrame {

    private ControladorDeEnvios controlador;

    public VentanaPrincipal(ControladorDeEnvios controlador) {
        this.controlador = controlador;

        setTitle("SpeedFast - Sistema de Entregas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Pedidos - SpeedFast", JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        //PAnel de botones
        JPanel panelBotones = new JPanel(new GridLayout(4,1,10,10));


        JButton btnRegistroPedido = new JButton("Registrar pedido");
        JButton btnAsignar = new JButton("Asignar Repartidor");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnRegistroRepartidor = new JButton("Registrar Repartidor");
        JButton btnEliminar = new JButton("Eliminar pedido");
        JButton btnEliminarRepartidor = new JButton("Eliminar Repartidor");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnRegistroPedido);
        panelBotones.add(btnAsignar);
        panelBotones.add(btnListar);
        panelBotones.add(btnRegistroRepartidor);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEliminarRepartidor);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        //EVENTOS DE LOS BOTONES

        //REGISTRAR PEDIDO
        btnRegistroPedido.addActionListener(e ->
                new VentanaRegistroPedido(controlador));

        //LISTAR PEDIDOS
        btnListar.addActionListener(e->
                new VentanaListaPedidos(controlador));

        //ASIGNAR REPARTIDOR
        btnAsignar.addActionListener(e ->
                new VentanaAsignarRepartidor(controlador));

        // ELIMINAR PEDIDO
        btnEliminar.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Ingrese el ID del pedido que desea eliminar: ");
            if (input == null || input.isEmpty()) return;

            int idEliminar;
            try {
                idEliminar = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido");
                return;
            }

            // Buscar pedido en memoria
            Pedido pedidoAEliminar = controlador.getListaPedidos().stream()
                    .filter(p -> p.getIdPedido() == idEliminar)
                    .findFirst()
                    .orElse(null);

            if (pedidoAEliminar == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el pedido con ID " + idEliminar);
                return;
            }

            // Eliminar de la BD
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.eliminar(pedidoAEliminar);

            // Eliminar de la lista
            controlador.getListaPedidos().remove(pedidoAEliminar);

            JOptionPane.showMessageDialog(this, "Pedido eliminado correctamente");
        });

        //REGISTRAR REPARTIDOR
        btnRegistroRepartidor.addActionListener(e -> new VentanaRegistroRepartidor());

        //ELIMINAR REPARTIDOR
        btnEliminarRepartidor.addActionListener(e -> new VentanaEliminarRepartidor());

        // SALIR
        btnSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea salir del sistema?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            });

        setVisible(true);
    }
}
