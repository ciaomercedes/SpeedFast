package cl.speedfast.app;

//nueva clase semana3

import cl.speedfast.interfaces.*;
import cl.speedfast.model.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios implements Despachable, Cancelable, Rastreable {

    private List<Pedido> historialEntregas = new ArrayList<>();

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
