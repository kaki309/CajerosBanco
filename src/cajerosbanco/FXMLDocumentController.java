/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cajerosbanco;

import cola.Cola;
import modelo.Cliente;
import modelo.Cajero;

import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/** 
 * Clase FXMLDocumentController para manejar la ejecución del programa
 * 
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Romero Código 2225751
 * @date 24 Febrero 2024
 * @version 1.0 
 */
public class FXMLDocumentController implements Initializable {

    /////////////////////////////////Variables de interfaz gráfica
    @FXML
    private TextArea txtColaNatural;
    @FXML
    private TextArea txtColaClientes;
    @FXML
    private TextArea txtColaPreferencial;
    @FXML
    private TextArea txtClientesAtendidos;
    @FXML
    private Text textCajero1;
    @FXML
    private Circle idCircle1;
    @FXML
    private Text textCajero2;
    @FXML
    private Circle idCircle2;
    @FXML
    private Text textCajero3;
    @FXML
    private Circle idCircle3;
    @FXML
    private Text textCajero4;
    @FXML
    private Circle idCircle4;
    @FXML
    private Text textCajero5;
    @FXML
    private Circle idCircle5;
    @FXML
    private Text textCajero6;
    @FXML
    private Circle idCircle6;

    /////////////////////////////////Variables de funcionamiento
    Cola<Cliente> colaNatural;
    Cola<Cliente> colaClientes;
    Cola<Cliente> colaPreferencial;
    Cajero cajero1;
    Cajero cajero2;
    Cajero cajero3;
    Cajero cajero4;
    Cajero cajero5;
    Cajero cajero6;
    Random random;
    LinkedList<Cliente> clientesAtendidos;
    private Timer timerCajeros;
    private ScheduledExecutorService executor;
    private Future<?> futureTask;

    /////////////////////////////////METODOS
    
    /**
     * Inicia la creación de Clientes, la ejecución de los cajeros, la
     * actualiación periódica de las colas y la actualización periódica de los
     * clientes ya atendidos
     *
     * @param event, para recibir la señal que se ha presionado un botón
     */
    @FXML
    public void iniciar(ActionEvent event) {
        crearClientes();

        timerCajeros = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {

                //Atender Clientes
                if (!colaNatural.estaVacia() && !cajero1.isEstaEjecutando()) {
                    cajero1.run(colaNatural, idCircle1, textCajero1, clientesAtendidos);
                }
                if (!colaNatural.estaVacia() && !cajero2.isEstaEjecutando()) {
                    cajero2.run(colaNatural, idCircle2, textCajero2, clientesAtendidos);
                }
                if (!colaClientes.estaVacia() && !cajero3.isEstaEjecutando()) {
                    cajero3.run(colaClientes, idCircle3, textCajero3, clientesAtendidos);
                }
                if (!colaClientes.estaVacia() && !cajero4.isEstaEjecutando()) {
                    cajero4.run(colaClientes, idCircle4, textCajero4, clientesAtendidos);
                }
                if (!colaPreferencial.estaVacia() && !cajero5.isEstaEjecutando()) {
                    cajero5.run(colaPreferencial, idCircle5, textCajero5, clientesAtendidos);
                }
                if (!colaPreferencial.estaVacia() && !cajero6.isEstaEjecutando()) {
                    cajero6.run(colaPreferencial, idCircle6, textCajero6, clientesAtendidos);
                }

                //Actualizar colas
                if (colaNatural.estaVacia()) {
                    Platform.runLater(() -> {
                        txtColaNatural.setText("[COLA VACÍA]");
                    });
                } else {
                    Platform.runLater(() -> {
                        txtColaNatural.setText(mostrarColas(colaNatural));
                    });
                }

                if (colaClientes.estaVacia()) {
                    Platform.runLater(() -> {
                        txtColaClientes.setText("[COLA VACÍA]");
                    });
                } else {
                    Platform.runLater(() -> {
                        txtColaClientes.setText(mostrarColas(colaClientes));
                    });
                }

                if (colaPreferencial.estaVacia()) {
                    Platform.runLater(() -> {
                        txtColaPreferencial.setText("[COLA VACÍA]");
                    });
                } else {
                    Platform.runLater(() -> {
                        txtColaPreferencial.setText(mostrarColas(colaPreferencial));
                    });
                }

                //Actualizar registro de clientes atendidos
                Platform.runLater(() -> {
                    txtClientesAtendidos.setText(mostrarAtendidos());
                });
            }
        };
        timerCajeros.scheduleAtFixedRate(task, 3000, 1000);
    }

    /**
     * Finaliza la creación de nuevos Clientes y espera a que los cajeros
     * terminen de atender al cliente que tienen en el momento. Luego genera el
     * reporte final de cada uno de los cajeros y lo muestra en pantalla
     *
     * @param event, para recibir la señal que se ha presionado un botón
     */
    @FXML
    public void finalizar(ActionEvent event) {
        if (futureTask != null) {
            futureTask.cancel(true);
        }
        if (executor != null) {
            executor.shutdown();
        }
        if (timerCajeros != null) {
            timerCajeros.cancel();
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            public void run() {

                if (!cajero1.isEstaEjecutando()
                        && !cajero2.isEstaEjecutando()
                        && !cajero3.isEstaEjecutando()
                        && !cajero4.isEstaEjecutando()
                        && !cajero5.isEstaEjecutando()
                        && !cajero6.isEstaEjecutando()) {

                    String reporte = "Clientes que quedaron en cola: " + calcularCantidadEnCola() + "\n"
                            + "\n"
                            + "Clientes atendidos por el Cajero 1: " + cajero1.clientesAtendidos() + "\n"
                            + "Tiempo total empleado: " + cajero1.tiempoAtencion() + " Segundos\n"
                            + "Promedio de tiempo: " + String.format("%.2f", cajero1.promedioTiempo()) + " Segundos\n"
                            + "Edad promedio atendida: " + cajero1.promedioEdad() + " Años\n"
                            + "Edad más avanzada atendida: " + cajero1.edadMasAvanzada() + " Años\n"
                            + "\n"
                            + "Clientes atendidos por el Cajero 2: " + cajero2.clientesAtendidos() + "\n"
                            + "Tiempo total empleado: " + cajero2.tiempoAtencion() + " Segundos\n"
                            + "Promedio de tiempo: " + String.format("%.2f", cajero2.promedioTiempo()) + " Segundos\n"
                            + "Edad promedio atendida: " + cajero2.promedioEdad() + " Años\n"
                            + "Edad más avanzada atendida: " + cajero2.edadMasAvanzada() + " Años\n"
                            + "\n"
                            + "Clientes atendidos por el Cajero 3: " + cajero3.clientesAtendidos() + "\n"
                            + "Tiempo total empleado: " + cajero3.tiempoAtencion() + " Segundos\n"
                            + "Promedio de tiempo: " + String.format("%.2f", cajero3.promedioTiempo()) + " Segundos\n"
                            + "Edad promedio atendida: " + cajero3.promedioEdad() + " Años\n"
                            + "Edad más avanzada atendida: " + cajero3.edadMasAvanzada() + " Años\n"
                            + "\n"
                            + "Clientes atendidos por el Cajero 4: " + cajero4.clientesAtendidos() + "\n"
                            + "Tiempo total empleado: " + cajero4.tiempoAtencion() + " Segundos\n"
                            + "Promedio de tiempo: " + String.format("%.2f", cajero4.promedioTiempo()) + " Segundos\n"
                            + "Edad promedio atendida: " + cajero4.promedioEdad() + " Años\n"
                            + "Edad más avanzada atendida: " + cajero4.edadMasAvanzada() + " Años\n"
                            + "\n"
                            + "Clientes atendidos por el Cajero 5: " + cajero5.clientesAtendidos() + "\n"
                            + "Tiempo total empleado: " + cajero5.tiempoAtencion() + " Segundos\n"
                            + "Promedio de tiempo: " + String.format("%.2f", cajero5.promedioTiempo()) + " Segundos\n"
                            + "Edad promedio atendida: " + cajero5.promedioEdad() + " Años\n"
                            + "Edad más avanzada atendida: " + cajero5.edadMasAvanzada() + " Años\n"
                            + "\n"
                            + "Clientes atendidos por el Cajero 6: " + cajero6.clientesAtendidos() + "\n"
                            + "Tiempo total empleado: " + cajero6.tiempoAtencion() + " Segundos\n"
                            + "Promedio de tiempo: " + String.format("%.2f", cajero6.promedioTiempo()) + " Segundos\n"
                            + "Edad promedio atendida: " + cajero6.promedioEdad() + " Años\n"
                            + "Edad más avanzada atendida: " + cajero6.edadMasAvanzada() + " Años";

                    JOptionPane.showMessageDialog(null, reporte);
                    timer.cancel();
                } else {
                    Platform.runLater(() -> {
                        txtClientesAtendidos.setText(mostrarAtendidos());
                    });
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * Crea de manera aleatoria con datos aleatorios los Clientes que son
     * encolados en cada una de las 3 colas de manera aleatoria
     */
    public void crearClientes() {

        executor = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                //Crear cliente
                int edadMinima = 14; // Valor mínimo de la edad
                int edadMaxima = 99; // Valor máximo de la edad
                int tiempoMinimo = 5; // Valor mínimo del tiempo para atender
                int tiempoMaximo = 20; // Valor máximo del tiempo para atender
                int colaMin = 1;
                int colaMax = 3;

                int edadAleatoria = random.nextInt((edadMaxima - edadMinima) + 1) + edadMinima;
                int tiempoAleatorio = random.nextInt((tiempoMaximo - tiempoMinimo) + 1) + tiempoMinimo;
                Cliente objCliente = new Cliente(edadAleatoria, tiempoAleatorio);

                int asignarCola = random.nextInt((colaMax - colaMin) + 1) + colaMin;

                //Encolar Natural
                if (asignarCola == 1) {
                    colaNatural.encolar(objCliente);
                    Platform.runLater(() -> {
                        txtColaNatural.setText(mostrarColas(colaNatural));
                    });
                }
                //Encolar Clientes
                if (asignarCola == 2) {
                    colaClientes.encolar(objCliente);
                    Platform.runLater(() -> {
                        txtColaClientes.setText(mostrarColas(colaClientes));
                    });
                }
                //Encolar Preferencial
                if (asignarCola == 3) {
                    colaPreferencial.encolar(objCliente);
                    Platform.runLater(() -> {
                        txtColaPreferencial.setText(mostrarColas(colaPreferencial));
                    });
                }

                // Programa la próxima ejecución con un periodo aleatorio
                int delay = 1 + random.nextInt(3); // Delay entre 1 y 4 segundos
                futureTask = executor.schedule(this, delay, TimeUnit.SECONDS);
            }
        };
        futureTask = executor.schedule(task, 0, TimeUnit.SECONDS);

    }

    /**
     * Toma los objetos de la Cola y los muestra en una cadena de manera
     * vertical según fueron añadidos (de arriba a abajo)
     *
     * @param cola, la Cola a pasar a String
     * @return el valor de cadena
     */
    public String mostrarColas(Cola<Cliente> cola) {

        String linea = cola.toString();
        String[] info;
        String cadena = "";

        info = linea.split("seg.");

        for (int i = 0; i < info.length; i++) {

            cadena += info[i] + "\n";
        }
        return cadena;
    }

    /**
     * Toma los objetos de la LinkedList clientesAtendidos y los muestra en una
     * cadena de manera vertical según fueron añadidos (de arriba a abajo)
     *
     * @return el valor de cadena
     */
    public String mostrarAtendidos() {

        String linea = clientesAtendidos.toString();
        String[] info;
        String cadena = "";

        info = linea.split("seg.");

        for (int i = 0; i < info.length; i++) {

            cadena += info[i] + "\n";
        }
        return cadena;
    }

    /**
     * Calcula la cantidad de Clientes que quedaron en las 3 colas sin ser
     * atendidos
     *
     * @return el valor de cantidad
     */
    public int calcularCantidadEnCola() {
        String linea = colaNatural.toString();
        String[] info = linea.split("seg.");
        int cantidad = info.length - 1;

        String linea2 = colaClientes.toString();
        String[] info2 = linea2.split("seg.");
        cantidad += info2.length - 1;

        String linea3 = colaPreferencial.toString();
        String[] info3 = linea3.split("seg.");
        cantidad += info3.length - 1;
        return cantidad;
    }

    /**
     * Es el constructor del programa, inicia las variables de funcionamiento
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colaNatural = new Cola<>();
        colaClientes = new Cola<>();
        colaPreferencial = new Cola<>();
        clientesAtendidos = new LinkedList<>();
        random = new Random();
        cajero1 = new Cajero();
        cajero2 = new Cajero();
        cajero3 = new Cajero();
        cajero4 = new Cajero();
        cajero5 = new Cajero();
        cajero6 = new Cajero();

    }

}
