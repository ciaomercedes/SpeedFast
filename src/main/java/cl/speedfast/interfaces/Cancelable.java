package cl.speedfast.interfaces;

import cl.speedfast.model.Pedido;

public interface Cancelable {
    void cancelar(Pedido pedido);
}
