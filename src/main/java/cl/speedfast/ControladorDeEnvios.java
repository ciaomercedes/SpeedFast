package cl.speedfast;

//nueva clase semana3

import java.util.ArrayList;

public class ControladorDeEnvios implements Despachable, Cancelable, Rastreable {

    private ArrayList<Pedido> historialEntregas = new  ArrayList<>();
    private ArrayList<Pedido> historialPedidosCancelados = new  ArrayList<>();

    @Override
    public void despachar(Pedido pedido) {
        System.out.println("Pedido: " + pedido.getIdPedido() + " (" + pedido.getTipoPedido() + ") despachado correctamente");
        historialEntregas.add(pedido);
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("Pedido " + pedido.getIdPedido() + " (" + pedido.getTipoPedido() + ") ha sido cancelado");
    }

    @Override
    public void verHistorialEntregas() {
        System.out.println("\n HISTORIAL DE ENTREGAS:");
        if (historialEntregas.isEmpty()) {
            System.out.println("No hay pedidos registrados");
        } else {
            for (Pedido p : historialEntregas) {
                System.out.println("- Pedido " + p.getIdPedido() + " | Tipo: "  + p.getTipoPedido());
            }
        }
    }
}
