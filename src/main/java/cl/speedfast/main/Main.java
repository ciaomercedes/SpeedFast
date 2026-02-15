package cl.speedfast.main;

import cl.speedfast.controladores.ControladorDeEnvios;
import cl.speedfast.vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        new VentanaPrincipal(controlador);

    }
}