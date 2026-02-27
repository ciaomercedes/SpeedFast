package cl.speedfast.vista;

import javax.swing.*;
import java.awt.*;
import cl.speedfast.modelo.*;
import cl.speedfast.dao.*;

public class VentanaRegistroRepartidor extends javax.swing.JFrame {

    public VentanaRegistroRepartidor() {

        setTitle("Registrar Repartidor Nuevo");
        setSize(300,150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,2,5,5));

        JTextField txtNombre = new JTextField();
        JButton btnGuardar = new JButton("Guardar");

        add(new JLabel("Nombre del repartidor: "));
        add(txtNombre);
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            String nombre = txtNombre.getText().trim();
            if(nombre.isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese un nombre válido");
                return;
            }

            // Guardar en la BD
            RepartidorDAO repartidorDAO = new RepartidorDAO();
            repartidorDAO.crear(new Repartidor(0, nombre, null, null));

            JOptionPane.showMessageDialog(this, "Repartidor registrado correctamente");
            dispose();
        });

        setVisible(true);
    }
}
