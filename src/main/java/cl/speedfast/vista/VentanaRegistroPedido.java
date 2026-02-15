package cl.speedfast.vista;

import cl.speedfast.modelo.*;
import cl.speedfast.controladores.ControladorDeEnvios;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends javax.swing.JFrame {

    public VentanaRegistroPedido(ControladorDeEnvios controlador) {

        setTitle("Registrar Pedido");
        setSize(350,250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,2,5,5));

        JTextField txtId = new JTextField();
        JTextField txtDireccion = new JTextField();

        String[] tipos = {"Comida","Encomienda","Express"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);

        JButton btnGuardar = new JButton("Guardar");

        add(new JLabel("ID:"));
        add(txtId);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Tipo:"));
        add(comboTipo);
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            if(txtId.getText().isEmpty() || txtDireccion.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "Complete todos los campos");
                return;
            }

            int id = Integer.parseInt(txtId.getText());
            String direccion = txtDireccion.getText();
            String tipo = comboTipo.getSelectedItem().toString();

            Pedido pedido;

            switch (tipo){
                case "Comida":
                    pedido = new PedidoComida(id,direccion,3);
                    break;
                case "Encomienda":
                    pedido = new PedidoEncomienda(id,direccion,3);
                    break;
                default:
                    pedido = new PedidoExpress(id,direccion,3);
            }

            controlador.agregarPedido(pedido);

            JOptionPane.showMessageDialog(this,
                    "Pedido registrado correctamente");

            dispose();
        });

        setVisible(true);
    }
}
