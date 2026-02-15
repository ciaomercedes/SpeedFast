package cl.speedfast.modelo;

public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, "Comida", distanciaKm);
    }

    //tiempo de entrega calculo
    @Override
    public int calcularTiempoEntrega() {
        return 15 + (int)(2 * distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("\n[PEDIDO COMIDA]");
        System.out.println("Buscando repartidor con mochila térmica disponible...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Repartidor " + nombreRepartidor +
                " ha sido asignado a su pedido de Comida. Mochila térmica verificada exitosamente");
    }
}
