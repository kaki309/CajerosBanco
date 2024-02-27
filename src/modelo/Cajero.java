/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Cola;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author andre
 */
public class Cajero {

    public void run(Cola<Cliente> cola, Circle forma, Text txtDuracion) {
        if (!cola.estaVacia()) {
            Cliente elemento = cola.desencolar();
            int tiempo = elemento.getTiempoAtencion();

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                int i = 0;

                @Override
                public void run() {
                    forma.setStroke(Color.RED);

                    txtDuracion.setText(String.valueOf(tiempo - i));

                    if (i == tiempo) {
                        forma.setStroke(Color.GREEN);
                        txtDuracion.setText("XX");
                        cancel();
                    }
                    i++;
                }

            };
            timer.scheduleAtFixedRate(task, 0, 1000);
        }

    }

}

