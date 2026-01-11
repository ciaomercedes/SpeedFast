package cl.speedfast;

public class Main {

    public static void main(String[] args) {
        //referencia al tipo de pedido
        Pedido pedido1 = new PedidoComida(1, "Av. Pedro de Valdivia 54, Providencia");
        Pedido pedido2 = new PedidoEncomienda(2, "Av. Augusto Leguia Norte 44, Las Condes");
        Pedido pedido3 = new PedidoExpress(3, "Presidente Riesco 5111, Las condes");

        System.out.println("\n>>>>> Gracias por elegir SpeedFast!");

        pedido1.asignarRepartidor();
        pedido2.asignarRepartidor();
        pedido3.asignarRepartidor();

        System.out.println("\nPor favor espere, esto podría tardar unos minutos...");

        System.out.println("\n>>>>> Asignación de repartidores para pedidos <<<<<");
        pedido1.asignarRepartidor("Pedro Muñoz");
        pedido2.asignarRepartidor("Marcela Morales");
        pedido3.asignarRepartidor("Andrés Perez");
    }
}
