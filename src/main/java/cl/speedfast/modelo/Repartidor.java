package cl.speedfast.modelo;

import cl.speedfast.controladores.ControladorDeEnvios;
import cl.speedfast.dao.*;
import java.util.List;

public class Repartidor implements Runnable {

    private int idRepartidor;
    private String nombre;
    private ZonaDeCarga zonaDeCarga;
    private ControladorDeEnvios controlador;

    public static final List<String> NOMBRES_FIJOS = List.of(
            "Daniel Muñoz",
            "Marcela Morales",
            "Carlos Espina"
    );

    public Repartidor(int idRepartidor, String nombre, ZonaDeCarga zonaDeCarga, ControladorDeEnvios controlador) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
        this.controlador = controlador;
    }

    @Override
    public void run() {

        System.out.println("Repartidor: " + nombre + " listo para comenzar ruta de despacho");
        System.out.println("---------------------------------------------------------------------------------------");

        PedidoDAO pedidoDAO = new PedidoDAO();

        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break; // no quedan pedidos
            }
            //Se empieza el flujo de pedido
            pedido.setEstado(EstadoPedido.EN_REPARTO);
            pedido.setNombreRepartidor(nombre);
            pedidoDAO.actualizarEstado(pedido); // se actualiza en BD

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
            pedidoDAO.actualizarEstado(pedido); // se actualiza en BD

            EntregaDAO entregaDAO = new EntregaDAO();
            entregaDAO.guardar(pedido, this); // Inserta id_pedido, id_repartidor, fecha, hora

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

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }
}