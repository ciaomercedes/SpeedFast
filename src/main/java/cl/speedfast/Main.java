package cl.speedfast;

public class Main {

    public static void main(String[] args) {

        //creacion de los objetos por cada tipo de pedido
        Pedido pedidoComida1 = new PedidoComida(1, "Av. Pedro de Valdivia 54, Providencia", 4.5);
        Pedido pedidoEncomienda1 = new PedidoEncomienda(2, "Av. Augusto Leguia Norte 44, Las Condes", 6.7);
        Pedido pedidoExpress1 = new PedidoExpress(3, "Presidente Riesco 5111, Las condes", 3.0);

        ControladorDeEnvios controlador = new ControladorDeEnvios(); // SEMANA 3

        System.out.println("\n>>>>> Gracias por elegir SpeedFast!\n");

        //COMIDA
        System.out.println("Resumen del Pedido de Comida:");
        pedidoComida1.mostrarResumen(); // Metodo nuevo de la semana2
        pedidoComida1.asignarRepartidor();
        System.out.println("Por favor espere, esto podría tardar unos minutos...");
        pedidoComida1.asignarRepartidor("Pedro Muñoz");
        System.out.println("Tiempo Estimado de Entrega: " + pedidoComida1.calcularTiempoEntrega() + " minutos\n");
        controlador.despachar(pedidoComida1); // Metodo nuevo de la semana3

        //ENCOMIENDA
        System.out.println("\nResumen del Pedido de Encomienda:");
        pedidoEncomienda1.mostrarResumen(); // Metodo nuevo de la semana2
        pedidoEncomienda1.asignarRepartidor();
        System.out.println("Por favor espere, esto podría tardar unos minutos...");
        pedidoEncomienda1.asignarRepartidor("Marcela Morales");
        System.out.println("Tiempo Estimado de Entrega: " + pedidoEncomienda1.calcularTiempoEntrega() + " minutos\n");
        controlador.despachar(pedidoEncomienda1); // Metodo nuevo de la semana3

        //EXPRESS
        System.out.println("\nResumen del Pedido de Express:");
        pedidoExpress1.mostrarResumen(); // Metodo nuevo de la semana2
        pedidoExpress1.asignarRepartidor();
        System.out.println("Por favor espere, esto podría tardar unos minutos...");
        System.out.println("Lo sentimos. No hemos podido encontrar un repartidor. La compra será cancelada.");
        controlador.cancelar(pedidoExpress1); // Metodo nuevo de la semana3

        //HISTORIAL DE ENTREGAS SEMANA 3
        controlador.verHistorialEntregas();
    }
}
