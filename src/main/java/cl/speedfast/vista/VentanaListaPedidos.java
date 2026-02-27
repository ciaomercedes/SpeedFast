package cl.speedfast.vista;

import cl.speedfast.dao.PedidoDAO;
import cl.speedfast.modelo.Pedido;
import cl.speedfast.controladores.ControladorDeEnvios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private DefaultTableModel modeloTabla;

    public VentanaListaPedidos(ControladorDeEnvios controlador) {

        setTitle("Lista de Pedidos");
        setSize(600,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Pedido");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Estado");

        JTable tabla = new JTable(modeloTabla);

        cargarDatos(controlador);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        setVisible(true);
    }

    private void cargarDatos(ControladorDeEnvios controlador){
        modeloTabla.setRowCount(0);

        PedidoDAO pedidoDAO = new PedidoDAO();
        List<Pedido> lista = pedidoDAO.leerTodos(); //se los trae desde la BD
        lista.forEach(pedido -> modeloTabla.addRow(new Object[]{
                pedido.getIdPedido(),
                pedido.getDireccionEntrega(),
                pedido.getTipoPedido(),
                pedido.getEstado()
        }));
    }
}
