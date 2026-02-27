package cl.speedfast.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import cl.speedfast.controladores.ControladorDeEnvios;
import cl.speedfast.modelo.*;
import cl.speedfast.dao.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VentanaAsignarRepartidor extends javax.swing.JFrame {

    public VentanaAsignarRepartidor(ControladorDeEnvios controlador) {

        setTitle("Asignar Repartidor");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Elija el pedido al cual se asignará un repartidor", JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnExpress = new JButton("Pedido Express");
        JButton btnComida = new JButton("Pedido Comida");
        JButton btnEncomienda = new JButton("Pedido Encomienda");

        panelBotones.add(btnExpress);
        panelBotones.add(btnComida);
        panelBotones.add(btnEncomienda);

        add(panelBotones, BorderLayout.CENTER);

        //Evento para cada boton
        btnExpress.addActionListener(e -> asignarRepartidor(controlador, "Compra Express"));
        btnComida.addActionListener(e -> asignarRepartidor(controlador, "Comida"));
        btnEncomienda.addActionListener(e -> asignarRepartidor(controlador, "Encomienda"));

        setVisible(true);
    }

    private void asignarRepartidor(ControladorDeEnvios controlador, String tipoPedido) {

        // Cargar repartidores desde BD
        RepartidorDAO repartidorDAO = new RepartidorDAO();
        List<Repartidor> repartidoresBD = repartidorDAO.leerTodos();

        JComboBox<String> comboRepartidor = new JComboBox<>();
        for (Repartidor r : repartidoresBD) {
            comboRepartidor.addItem(r.getIdRepartidor() + " - " + r.getNombre());
        }

        int opcion = JOptionPane.showConfirmDialog(
                null,
                comboRepartidor,
                "Asignar repartidor",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion != JOptionPane.OK_OPTION) return;

        // Obtener ID y nombre desde el combo
        String seleccionado = comboRepartidor.getSelectedItem().toString();
        int idRepartidor = Integer.parseInt(seleccionado.split(" - ")[0]);
        String nombreRepartidor = seleccionado.split(" - ")[1];

        // Filtrar pedidos pendientes del tipo seleccionado
        List<Pedido> listaFiltrada = controlador.getListaPedidos().stream()
                .filter(p -> p.getTipoPedido().equals(tipoPedido) && p.getEstado() == EstadoPedido.PENDIENTE)
                .toList();

        if (listaFiltrada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos " + tipoPedido + " pendientes");
            return;
        }

        // Actualizar estado de pedidos en BD
        PedidoDAO pedidoDAO = new PedidoDAO();
        for (Pedido p : listaFiltrada) {
            p.setEstado(EstadoPedido.EN_REPARTO);
            p.setNombreRepartidor(nombreRepartidor);
            pedidoDAO.actualizarEstado(p);

            // Creamos la zona de carga pero solo con los pedidos filtrados
            ZonaDeCarga zona = new ZonaDeCarga();
            listaFiltrada.forEach(zona::agregarPedido);

            // Ejecutar repartidor en hilo
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Repartidor(idRepartidor, nombreRepartidor, zona, controlador));
            executor.shutdown();

            JOptionPane.showMessageDialog(null,
                    "Repartidor " + nombreRepartidor + " asignado a " + listaFiltrada.size() + " pedido(s) de tipo " + tipoPedido);
            dispose();
        }
    }
}
