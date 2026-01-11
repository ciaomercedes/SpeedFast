package cl.speedfast;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Encomienda");
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
