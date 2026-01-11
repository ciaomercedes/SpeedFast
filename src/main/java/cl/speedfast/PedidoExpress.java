package cl.speedfast;

import java.sql.SQLOutput;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Compra Express");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("\n[COMPRA EXPRESS]");
        System.out.println("-Asignando repartidor más cercano disponible...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("\n[COMPRA EXPRESS]");
        System.out.println("Repartidor " + nombreRepartidor +
                " ha sido asignado a su compra Express exitosamente");
    }
}
