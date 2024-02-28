/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Cola;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
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
    private LinkedList<Cliente> registroClientes = new LinkedList<>();

    public LinkedList<Cliente> getRegistroClientes() {
        return registroClientes;
    }

    public void setRegistroClientes(LinkedList<Cliente> registroClientes) {
        this.registroClientes = registroClientes;
    }

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

    public void run(Cola<Cliente> cola, Circle forma, Text txtDuracion, LinkedList<Cliente> atendidos) {
        Cliente elemento = cola.desencolar();
        Cliente copia = (Cliente) elemento.copy();
        estaEjecutando = true;
        int tiempo = elemento.getTiempoAtencion();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            int i = 0;

            @Override
            public void run() {
                forma.setStroke(Color.RED);

                Platform.runLater(() -> {
                    txtDuracion.setText(String.valueOf((tiempo - i)+1));
                });

                if (i == tiempo) {
                    Platform.runLater(() -> {
                        forma.setStroke(Color.GREEN);
                        txtDuracion.setText("XX");
                    });
                    atendidos.addLast(elemento);
                    registroClientes.addLast(copia);
                    estaEjecutando = false;
                    cancel();
                }
                i++;
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public int clientesAtendidos() {
        int cantidad = registroClientes.size();
        return cantidad;
    }

    public int tiempoAtencion() {
        int tiempo = 0;
        for (int i = 0; i < registroClientes.size(); i++) {

            tiempo += registroClientes.get(i).getTiempoAtencion();
        }
        return tiempo;
    }

    public double promedioTiempo() {
        double promedioTiempo = 0;
        double tiempo = (double) tiempoAtencion();
        
        promedioTiempo =  tiempo/registroClientes.size();

        return promedioTiempo;
    }

    public int promedioEdad() {
        int promedioEdad = 0;
        int sumaEdad = 0;
        for (int i = 0; i < registroClientes.size(); i++) {

            sumaEdad += registroClientes.get(i).getEdad();
        }
        promedioEdad = sumaEdad / registroClientes.size();

        return promedioEdad;
    }

    public int edadMasAvanzada() {

        Collections.sort(registroClientes, new Comparator<Cliente>() {
            public int compare(Cliente c1, Cliente c2) {
                return Integer.compare(c1.getEdad(), c2.getEdad());
            }
        });
        return registroClientes.getLast().getEdad();
    }
}
