package cl.speedfast;

public class Pedido {

    protected int idPedido;
    protected String direcionEntrega;
    protected String tipoPedido;

    public Pedido(int idPedido, String direcionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direcionEntrega = direcionEntrega;
        this.tipoPedido = tipoPedido;
    }

    //Metodo general para sobreescribir
    public void asignarRepartidor() {
        System.out.println("Se ha asignado un repartidor para el pedido " + idPedido);
    }

    //Método sobrecargado
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Repartidor "+ nombreRepartidor +
                " asignado al pedido " + idPedido);
    }
}
