package cl.speedfast;

public abstract class Pedido {

    protected int idPedido;
    protected String direccionEntrega;
    protected String tipoPedido;
    protected double distanciaKm;

    public Pedido(int idPedido, String direcionEntrega, String tipoPedido, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direcionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm = distanciaKm;
    }

    public void mostrarResumen() {
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Direcion Entrega: " + direccionEntrega);
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

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }
}
