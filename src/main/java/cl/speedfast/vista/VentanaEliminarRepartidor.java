package cl.speedfast.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import cl.speedfast.dao.*;
import cl.speedfast.modelo.*;

public class VentanaEliminarRepartidor extends JFrame {

    private JComboBox<Repartidor> combo;
    private RepartidorDAO dao;

    public VentanaEliminarRepartidor() {

        dao = new RepartidorDAO();

        setTitle("Eliminar Repartidor");
        setSize(350,150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,2,5,5));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cargarCombo();

        JButton btnEliminar = new JButton("Eliminar");

        add(new JLabel("Seleccione repartidor:"));
        add(combo);
        add(new JLabel(""));
        add(btnEliminar);

        btnEliminar.addActionListener(e -> eliminarRepartidor());

        setVisible(true);
    }

    private void cargarCombo() {

        List<Repartidor> lista = dao.leerTodos();

        combo = new JComboBox<>(lista.toArray(new Repartidor[0]));
    }

    private void eliminarRepartidor() {

        Repartidor seleccionado =
                (Repartidor) combo.getSelectedItem();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this,
                    "No hay repartidores disponibles");
            return;
        }

        int id = seleccionado.getIdRepartidor();

        // 🔎 VALIDACIÓN IMPORTANTE
        if (dao.tieneEntregas(id)) {

            JOptionPane.showMessageDialog(this,
                    "No se puede eliminar.\n" +
                            "El repartidor tiene entregas asociadas.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirmación opcional (más profesional)
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar a " + seleccionado.getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            dao.eliminar(id);

            combo.removeItem(seleccionado);

            JOptionPane.showMessageDialog(this,
                    "Repartidor eliminado correctamente");
        }
    }
}