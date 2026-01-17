package cl.speedfast;

public abstract class Pedido {

    protected int idPedido;
    protected String direcionEntrega;
    protected String tipoPedido;
    protected double distanciaKm;

    public Pedido(int idPedido, String direcionEntrega, String tipoPedido, double distanciaKm) {
        this.idPedido = idPedido;
        this.direcionEntrega = direcionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm = distanciaKm;
    }

    //Nuevo metodo semana 2
    public void mostrarResumen() {
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Direcion Entrega: " + direcionEntrega);
        System.out.println("Tipo Pedido: " + tipoPedido);
        System.out.println("Distancia Km: " + distanciaKm);
    }

    //Metodo abstracto
    public abstract int calcularTiempoEntrega();

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
