/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Cola;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author andre
 */
public class Cajero {

    private String estado;

    public Cajero() {
    }

    public Cajero(String estado) {
        this.estado = estado;
    }

    /**
     * Get the value of estado
     *
     * @return the value of estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cajero{" + "estado=" + estado + '}';
    }

    public void ejecutar(Cola<Cliente> cola, LinkedList<Cliente> atendidos) {
        if (!cola.estaVacia()) {
            Cliente elemento = cola.desencolar();
            int tiempo = elemento.getTiempoAtencion();
            estado = "Libre";

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                public void run() {
                    estado = "Ocupado";
                }

            };
            timer.schedule(task, 3000, tiempo * 1000);
            atendidos.add(elemento);
            
        }

    }

}
