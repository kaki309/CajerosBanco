/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Cola;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Clase Cajero para crear cajeros con distintas entradas de las colas para
 * atender
 *
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Código
 * 2225751
 * @date 24 Febrero 2024
 * @version 1.0
 */
public class Cajero {

    private boolean estaEjecutando;

    /**
     * Get the value of estaEjecutando
     *
     * @return the value of estaEjecutando
     */
    public boolean isEstaEjecutando() {
        return estaEjecutando;
    }

    /**
     * Set the value of estaEjecutando
     *
     * @param estaEjecutando new value of estaEjecutando
     */
    public void setEstaEjecutando(boolean estaEjecutando) {
        this.estaEjecutando = estaEjecutando;
    }

    public void run(Cola<Cliente> cola, Circle forma, Text txtDuracion) {
        Cliente elemento = cola.desencolar();
        estaEjecutando = true;
        int tiempo = elemento.getTiempoAtencion();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            int i = 0;

            @Override
            public void run() {
                forma.setStroke(Color.RED);

                Platform.runLater(() -> {
                        txtDuracion.setText(String.valueOf(tiempo - i));
                    });

                if (i == tiempo) {
                    Platform.runLater(() -> {
                        forma.setStroke(Color.GREEN);
                        txtDuracion.setText("XX");
                    });
                    estaEjecutando = false;
                    cancel();
                }
                i++;
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

}
