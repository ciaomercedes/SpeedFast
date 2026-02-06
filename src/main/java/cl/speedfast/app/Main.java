package cl.speedfast.app;

import cl.speedfast.model.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        //creacion de los pedidos a la zona de carga
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("INICIANDO CARGAMENTO.....\n");
        zonaDeCarga.agregarPedido(new PedidoComida(1, "Providencia 123", 4.5));
        zonaDeCarga.agregarPedido(new PedidoEncomienda(2, "Las Condes 456", 6.7));
        zonaDeCarga.agregarPedido(new PedidoExpress(3, "José Pedro Alessandri 996", 3.7));
        zonaDeCarga.agregarPedido(new PedidoComida(4, "Ñuñoa 222", 3.2));
        zonaDeCarga.agregarPedido(new PedidoExpress(5, "Vitacura 888", 2.0));
        System.out.println("---------------------------------------------------------------------------------------");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Repartidor("Daniel Muñoz", zonaDeCarga, controlador));
        executor.execute(new Repartidor("Marcela Morales", zonaDeCarga, controlador));
        executor.execute(new Repartidor("Carlos Espina", zonaDeCarga, controlador));

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("\nTodos los pedidos han sido entregados correctamente");

        // HISTORIAL FINAL DE PEDIDOS ENTREGADOS
        controlador.verHistorialEntregas();
    }
}
