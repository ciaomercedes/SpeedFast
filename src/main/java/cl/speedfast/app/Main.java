package cl.speedfast.app;

import cl.speedfast.model.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        //creacion de los objetos por cada tipo de pedido
        Pedido p1 = new PedidoComida(1, "Providencia 123", 4.5);
        Pedido p2 = new PedidoEncomienda(2, "Las Condes 456", 6.7);
        Pedido p3 = new PedidoExpress(3, "José Pedro Alessandri 996", 3.7);

        Pedido p4 = new PedidoComida(4, "Ñuñoa 222", 3.2);
        Pedido p5 = new PedidoExpress(5, "Vitacura 888", 2.0);
        Pedido p6 = new PedidoEncomienda(6, "Presidente Riesco 8697", 8.6);

        Pedido p7 = new PedidoEncomienda(7, "Maipú 777", 8.1);
        Pedido p8 = new PedidoExpress(8, "La Reina 999", 4.0);
        Pedido p9 = new PedidoComida(9, "Las Condes 665", 7.1);

        Repartidor r1 = new Repartidor("Daniel Muñoz",
                List.of(p1, p2, p3), controlador);
        Repartidor r2 = new Repartidor("Marcela Morales",
                List.of(p4, p5, p6), controlador);
        Repartidor r3 = new Repartidor("Carlos Espina",
                List.of(p7, p8, p9), controlador);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(r1);
        executor.execute(r2);
        executor.execute(r3);

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        // HISTORIAL FINAL DE PEDIDOS ENTREGADOS
        controlador.verHistorialEntregas();
    }
}
