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
 * atender y generar el reporte de los clientes atendidos por el mismo
 *
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Romero Código 2225751
 * @date 24 Febrero 2024
 * @version 1.0
 */
public class Cajero {

    private boolean estaEjecutando;
    private LinkedList<Cliente> registroClientes = new LinkedList<>();

    /**
     * Toma el valor de registroClientes
     *
     * @return el valor de registroClientes
     */
    public LinkedList<Cliente> getRegistroClientes() {
        return registroClientes;
    }

    /**
     * Establece el valor de registroClientes
     *
     * @param registroClientes LinkedList de tipo Cliente
     */
    public void setRegistroClientes(LinkedList<Cliente> registroClientes) {
        this.registroClientes = registroClientes;
    }

    /**
     * Toma el valor de estaEjecutando
     *
     * @return el valor de estaEjecutando
     */
    public boolean isEstaEjecutando() {
        return estaEjecutando;
    }

    /**
     * Establece el valor de estaEjecutando
     *
     * @param estaEjecutando nuevo valor de estaEjecutando
     */
    public void setEstaEjecutando(boolean estaEjecutando) {
        this.estaEjecutando = estaEjecutando;
    }

    /**
     * Empieza la ejecución para atender a los clientes que están en la cola
     * según el tiempo de cada cliente
     *
     * @param cola, Cola a la que el cajero va a atender
     * @param forma, representación del cajero en el gráfico, para establecer su
     * estado como "Libre/ocupado"
     * @param txtDuracion, texto del gráfico que muestra el tiempo en estar
     * libre nuevamente el cajero
     * @param atendidos, LinkedList donde se guardarán los clientes que ya
     * terminaron de ser atendidos
     */
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
                    txtDuracion.setText(String.valueOf((tiempo - i) + 1));
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

    /**
     * Calcula el número de clientes atendidos por el cajero
     *
     * @return el valor de cantidad
     */
    public int clientesAtendidos() {
        int cantidad = registroClientes.size();
        return cantidad;
    }

    /**
     * Calcula el tiempo total de atención del cajero
     *
     * @return el valor de tiempo
     */
    public int tiempoAtencion() {
        int tiempo = 0;
        for (int i = 0; i < registroClientes.size(); i++) {

            tiempo += registroClientes.get(i).getTiempoAtencion();
        }
        return tiempo;
    }

    /**
     * Calcula el tiempo promedio de atención del cajero
     *
     * @return el valor de promedioTiempo
     */
    public double promedioTiempo() {
        double promedioTiempo = 0;
        double tiempo = (double) tiempoAtencion();

        promedioTiempo = tiempo / registroClientes.size();

        return promedioTiempo;
    }

    /**
     * Calcula la edad promedio atendida por el cajero
     *
     * @return el valor de promedioEdad
     */
    public int promedioEdad() {
        int promedioEdad = 0;
        int sumaEdad = 0;
        for (int i = 0; i < registroClientes.size(); i++) {

            sumaEdad += registroClientes.get(i).getEdad();
        }
        promedioEdad = sumaEdad / registroClientes.size();

        return promedioEdad;
    }

    /**
     * Calcula la edad más avanzada atendida por el cajero
     *
     * @return el valor de Edad del último elemento de la LinkedList
     * registroClientes
     */
    public int edadMasAvanzada() {

        Collections.sort(registroClientes, new Comparator<Cliente>() {
            public int compare(Cliente c1, Cliente c2) {
                return Integer.compare(c1.getEdad(), c2.getEdad());
            }
        });
        return registroClientes.getLast().getEdad();
    }
}
