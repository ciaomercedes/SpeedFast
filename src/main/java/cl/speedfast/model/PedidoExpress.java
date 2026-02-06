package cl.speedfast.model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, "Compra Express", distanciaKm);
    }

    //tiempo de entrega calculo
    @Override
    public int calcularTiempoEntrega() {
        if (distanciaKm > 5) {
            return 10 + 5; //10' de base más 5' extras
        }
        return 10; //solo 10' si es menor o igual a 5km
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("\n[COMPRA EXPRESS]");
        System.out.println("-Asignando repartidor más cercano disponible...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Repartidor " + nombreRepartidor +
                " ha sido asignado a su compra Express exitosamente");
    }
}
