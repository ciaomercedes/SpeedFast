package cl.speedfast.model;

import cl.speedfast.app.ControladorDeEnvios;
import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidosAsignados;
    private ControladorDeEnvios controlador;

    public Repartidor(String nombre, List<Pedido> pedidosAsignados, ControladorDeEnvios controlador) {
        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
        this.controlador = controlador;
    }

    @Override
    public void run() {
        Random random = new Random();

        System.out.println("Repartidor: " + nombre + " inició la ruta de entregas\n");

        for (Pedido pedido : pedidosAsignados) {
            System.out.println("-> " + nombre + " comenzó la entrega del pedido " + pedido.getIdPedido() + " (" + pedido.getTipoPedido() + ")");

            try {
                int tiempo = pedido.calcularTiempoEntrega();
                int pausa = random.nextInt(2000) + 1000; // de 1 a 3 segundos
                Thread.sleep(pausa);

                System.out.println("\nÉxito! " + nombre + " entregó el pedido "
                        + pedido.getIdPedido() + " (" + pedido.getTipoPedido()
                        + ") | Tiempo estimado: " + tiempo + " minutos");

                //guardamos el nombre del repartidor para el historial
                pedido.setNombreRepartidor(nombre);

                //registramos la entrega
                controlador.despachar(pedido);

            } catch (InterruptedException e) {
                System.out.println("Atención! Entrega interrumpida del pedido" + pedido.getIdPedido());
            }
        }
        System.out.println("Repartidor: " + nombre + " finalizó todas sus entregas.\n");
    }
}
