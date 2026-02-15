package cl.speedfast.vista;

import javax.swing.*;
import java.awt.*;
import cl.speedfast.controladores.ControladorDeEnvios;

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

        JPanel panelBotones = new JPanel(new GridLayout(4,1,10,10));

        JButton btnRegistroPedido = new JButton("Registrar pedido");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnAsignar = new JButton("Asignar Repartidor");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnRegistroPedido);
        panelBotones.add(btnListar);
        panelBotones.add(btnAsignar);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        //EVENTOS DE LOS BOTONES

        btnRegistroPedido.addActionListener(e ->
                new VentanaRegistroPedido(controlador));

        btnListar.addActionListener(e->
                new VentanaListaPedidos(controlador));

        btnAsignar.addActionListener(e ->
                new VentanaAsignarRepartidor(controlador));

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
