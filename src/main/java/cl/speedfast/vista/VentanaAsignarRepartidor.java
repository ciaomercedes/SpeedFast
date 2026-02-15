package cl.speedfast.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import cl.speedfast.controladores.ControladorDeEnvios;
import cl.speedfast.modelo.Pedido;
import cl.speedfast.modelo.EstadoPedido;
import cl.speedfast.modelo.Repartidor;
import cl.speedfast.modelo.ZonaDeCarga;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VentanaAsignarRepartidor extends javax.swing.JFrame {

    public VentanaAsignarRepartidor(ControladorDeEnvios controlador) {

        setTitle("Asignar Repartidor");
        setSize(500,200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Elija el pedido al cual se asignará un repartidor", JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(1,3,10,10));
        JButton btnExpress = new JButton("Pedido Express");
        JButton btnComida = new JButton("Pedido Comida");
        JButton btnEncomienda = new JButton("Pedido Encomienda");

        panelBotones.add(btnExpress);
        panelBotones.add(btnComida);
        panelBotones.add(btnEncomienda);

        add(panelBotones, BorderLayout.CENTER);

        //Evento para cada bvton
        btnExpress.addActionListener(e -> asignarRepartidor(controlador, "Compra Express"));
        btnComida.addActionListener(e -> asignarRepartidor(controlador, "Comida"));
        btnEncomienda.addActionListener(e -> asignarRepartidor(controlador, "Encomienda"));

        setVisible(true);
    }

    private void asignarRepartidor(ControladorDeEnvios controlador, String tipoPedido) {

        // Lista desplegable con los repartidores fijos
        JComboBox<String> comboRepartidor = new JComboBox<>(Repartidor.NOMBRES_FIJOS.toArray(new String[0]));
        JPanel panelSeleccion = new JPanel(new BorderLayout());
        panelSeleccion.add(new JLabel("Seleccione el repartidor:"), BorderLayout.NORTH);
        panelSeleccion.add(comboRepartidor, BorderLayout.CENTER);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panelSeleccion,
                "Asignar repartidor",
                JOptionPane.OK_CANCEL_OPTION
        );

        if(opcion != JOptionPane.OK_OPTION) return;

        String nombre = comboRepartidor.getSelectedItem().toString();

        // Filtrar pedidos pendientes del tipo seleccionado
        List<Pedido> listaFiltrada = controlador.getListaPedidos().stream()
                .filter(p -> p.getTipoPedido().equals(tipoPedido) && p.getEstado() == EstadoPedido.PENDIENTE)
                .toList();

        if(listaFiltrada.isEmpty()){
            JOptionPane.showMessageDialog(null,"No hay pedidos "+tipoPedido+" pendientes");
            return;
        }

        // Creamos la zona de carga pero solo con los pedidos filtrados
        ZonaDeCarga zona = new ZonaDeCarga();
        listaFiltrada.forEach(zona::agregarPedido);

        // Ejecutar repartidor en hilo
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Repartidor(nombre, zona, controlador));
        executor.shutdown();

        JOptionPane.showMessageDialog(null,
                "Repartidor "+nombre+" asignado a "+listaFiltrada.size()+" pedido(s) de tipo "+tipoPedido);
        dispose();
    }
}
