package cl.speedfast.controladores;

//nueva clase semana3

import cl.speedfast.interfaces.*;
import cl.speedfast.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios implements Despachable, Cancelable, Rastreable {

    private List<Pedido> historialEntregas = new ArrayList<>();
    private List<Pedido> listaPedidos = new ArrayList<>();

    public void agregarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    @Override
    public synchronized void despachar(Pedido pedido) {
        System.out.println("Pedido: " + pedido.getIdPedido() + " (" + pedido.getTipoPedido() + ") registrado como " +
                "ENTREGADO");
        historialEntregas.add(pedido);
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("Pedido " + pedido.getIdPedido() + " (" + pedido.getTipoPedido() + ") ha sido cancelado");
    }

    @Override
    public void verHistorialEntregas() {
        System.out.println("\n HISTORIAL FINAL DE ENTREGAS:");
        if (historialEntregas.isEmpty()) {
            System.out.println("No hay pedidos registrados");
            return;
        }
        for (Pedido p : historialEntregas) {
            System.out.println("- Pedido " + p.getIdPedido() + " ("  + p.getTipoPedido() + ") Entregado por: "
                    + p.getNombreRepartidor() +" | Dirección: " + p.getDireccionEntrega());
        }
    }

}
