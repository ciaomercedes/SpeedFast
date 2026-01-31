package cl.speedfast.model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, "Encomienda",distanciaKm);
    }

    //tiempo de entrega calculo
    @Override
    public int calcularTiempoEntrega() {
        return 20 + (int)(1.5 * distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("\n[PEDIDO ENCOMIENDA]");
        System.out.println("-Validando peso y embalaje de su pedido...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("\n[PEDIDO ENCOMIENDA]");
        System.out.println("Repartidor " + nombreRepartidor +
                " ha sido asignado para llevar su Encomienda. Peso y embalaje validados exitosamente");
    }
}
