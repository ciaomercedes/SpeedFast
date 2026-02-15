package cl.speedfast.modelo;

public abstract class Pedido {

    protected int idPedido;
    protected String direccionEntrega;
    protected String tipoPedido;
    protected double distanciaKm;
    protected String nombreRepartidor;
    protected EstadoPedido estado;

    public Pedido(int idPedido, String direccionEntrega, String tipoPedido, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm = distanciaKm;
        this.estado = EstadoPedido.PENDIENTE; //estado INICIAL
    }

    public void mostrarAsignacionCompleta (String nombreRepartidor) {
        asignarRepartidor(nombreRepartidor);

        System.out.println("Dirección de entrega: " + direccionEntrega);
        System.out.println("Tiempo estimado de entrega: " + calcularTiempoEntrega() + " minutos");
    }

    public void mostrarResumen() {
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Direccion Entrega: " + direccionEntrega);
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

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
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

    public EstadoPedido getEstado() {
        return estado;
    }

    public synchronized void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }
}
