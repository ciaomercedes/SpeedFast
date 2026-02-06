package cl.speedfast.model;

import cl.speedfast.app.ControladorDeEnvios;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaDeCarga zonaDeCarga;
    private ControladorDeEnvios controlador;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga, ControladorDeEnvios controlador) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
        this.controlador = controlador;
    }

    @Override
    public void run() {

        System.out.println("Repartidor: " + nombre + " listo para comenzar ruta de despacho");
        System.out.println("---------------------------------------------------------------------------------------");

        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break; // no quedan pedidos
            }
            //Se empieza el flujo de pedido
            pedido.setEstado(EstadoPedido.EN_REPARTO);

            synchronized(System.out) {

                                pedido.asignarRepartidor(); //Mensaje de 'buscando repartidor'
                pedido.setNombreRepartidor(nombre); //asignacion del repartidor
                pedido.mostrarAsignacionCompleta(nombre);
                System.out.println(nombre + " retiró pedido " + pedido.getIdPedido() + "\n");
            }

            System.out.println("---------------------------------------------------------------------------------------");

            try {
                Thread.sleep(pedido.calcularTiempoEntrega() * 100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            pedido.setEstado(EstadoPedido.ENTREGADO);
            controlador.despachar(pedido);

            synchronized (System.out) {
                System.out.println(nombre + " entregó pedido " + pedido.getIdPedido());
                System.out.println("Estado final del pedido "
                        + pedido.getIdPedido() + ": " + pedido.getEstado());
                System.out.println("---------------------------------------------------------------------------------------");
            }
        }

        System.out.println("Repartidor " + nombre + " terminó su ruta");
        System.out.println("---------------------------------------------------------------------------------------");
    }
}