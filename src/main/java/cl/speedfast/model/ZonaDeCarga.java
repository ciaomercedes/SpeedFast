package cl.speedfast.model;

import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {

    private Queue<Pedido> pedidos = new LinkedList<>();

    //solo un repartidor puede retirar un pedido a la vez, evitar que dos hilos saquen el mismo pedido
    public synchronized void agregarPedido(Pedido p) {
        pedidos.add(p);
        System.out.println("Pedido " + p.getIdPedido() + " agregado a la zona de carga");
    }

    public synchronized Pedido retirarPedido() {
        if (pedidos.isEmpty()) {
            return null;
        }
        return pedidos.poll();
    }
}
