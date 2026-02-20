package cl.speedfast.vista;

import cl.speedfast.modelo.*;
import cl.speedfast.controladores.ControladorDeEnvios;
import cl.speedfast.dao.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends javax.swing.JFrame {

    public VentanaRegistroPedido(ControladorDeEnvios controlador) {

        setTitle("Registrar Pedido");
        setSize(350,250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,2,5,5));

        JTextField txtDireccion = new JTextField();
        JTextField txtDistancia = new JTextField();

        String[] tipos = {"Comida","Encomienda","Express"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);

        JButton btnGuardar = new JButton("Guardar");

        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Tipo:"));
        add(comboTipo);
        add(new JLabel("Distancia (Km):"));
        add(txtDistancia);
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            if(txtDireccion.getText().isEmpty() || txtDistancia.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "Complete todos los campos");
                return;
            }

            String direccion = txtDireccion.getText();
            String tipo = comboTipo.getSelectedItem().toString();
            double distancia;

            try {
                distancia = Double.parseDouble(txtDistancia.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese un valor válido para distancia");
                return;
            }

            Pedido pedido;

            switch (tipo){
                case "Comida":
                    pedido = new PedidoComida(0,direccion, distancia);
                    break;
                case "Encomienda":
                    pedido = new PedidoEncomienda(0,direccion, distancia);
                    break;
                default:
                    pedido = new PedidoExpress(0,direccion, distancia);
            }

            // Guardar en la BD
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.guardar(pedido); // Aqui recuperamos el ID asignado por la base de datos y lo asigna y guarda

            controlador.agregarPedido(pedido);

            JOptionPane.showMessageDialog(this,
                    "Pedido registrado correctamente");

            dispose();
        });

        setVisible(true);
    }
}
