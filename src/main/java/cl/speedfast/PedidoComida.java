package cl.speedfast;

public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Comida");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("\n[PEDIDO COMIDA]");
        System.out.println("- Buscando repartidor con mochila térmica disponible...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("\n[PEDIDO COMIDA]");
        System.out.println("Repartidor " + nombreRepartidor +
                " ha sido asignado a su pedido de Comida. Mochila térmica verificada exitosamente");
    }
}
