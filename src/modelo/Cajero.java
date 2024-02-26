/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Cola;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author andre
 */
public class Cajero {

    public void ejecutar(Cola<Cliente> cola, LinkedList<Cliente> atendidos, Circle forma) {
        
        if (!cola.estaVacia()) {
            Cliente elemento = cola.desencolar();
            int tiempo = elemento.getTiempoAtencion();

            Timer timer = new Timer();
            Timer timer2 = new Timer();
            TimerTask task = new TimerTask() {

                public void run() {
                    forma.setStroke(Color.RED);
                    TimerTask task2 = new TimerTask() {

                        int i = 0;

                        public void run() {

                            if (i == tiempo) {
                                atendidos.add(elemento);
                                forma.setStroke(Color.GREEN);
                                timer.cancel();
                            }
                            i++;

                        }

                    };
                    timer2.scheduleAtFixedRate(task2, 0, 1000);

                }

            };
            timer.scheduleAtFixedRate(task, 3000, tiempo * 1000);
        }

    }

}
