/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cajerosbanco;

import cola.Cola;
import modelo.Cliente;

import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/*/** 
 * Clase FXML Controller para manejar la interfaz gráfica
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Código 2225751
 * @author valeria.garcia_perez@uao.edu.co Valeria García Pérez Código 2225662
 * @author juan_p.gutierrez@uao.edu.co Juan Pablo Gutierrez Código 2221673
 * @date 24 Febrero 2024
 * @version 1.0 
 */
public class FXMLDocumentController implements Initializable {

    //Montar todos los elementos necesarios con @FXML
    @FXML
    private TextArea txtArea;

    Cola<Cliente> colaNatural;
    Cola<Cliente> colaClientes;
    Cola<Cliente> colaPreferencial;

    LinkedList<Cliente> clientesAtendidos;
    TimerTask task;
    Timer timer;
    Random random;

    private ScheduledExecutorService executor;

    @FXML
    public void iniciar(ActionEvent event) {

        executor = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                //Crear cliente
                int edadMinima = 14; // Valor mínimo de la edad
                int edadMaxima = 99; // Valor máximo de la edad
                int tiempoMinimo = 3; // Valor mínimo del tiempo para atender
                int tiempoMaximo = 20; // Valor máximo del tiempo para atender
                int colaMin = 1;
                int colaMax = 30;

                int edadAleatoria = random.nextInt(edadMaxima - edadMinima + 1) + edadMinima;
                int tiempoAleatorio = random.nextInt(tiempoMaximo - tiempoMinimo + 1) + tiempoMinimo;
                Cliente objCliente = new Cliente(edadAleatoria, tiempoAleatorio);

                int asignarCola = random.nextInt(colaMax - colaMin + 1) + colaMin;
                //Encolar en Cola Natural
                if (asignarCola >= 1 && asignarCola <= 10) {
                    colaNatural.encolar(objCliente);
                    txtArea.setText(
                            "Cola asignada: Natural" + "\n"
                            + objCliente.toString());
                }
                //Encolar en Cola para Clientes
                if (asignarCola >= 11 && asignarCola <= 20) {
                    colaClientes.encolar(objCliente);
                    txtArea.setText(
                            "Cola asignada: Clientes" + "\n"
                            + objCliente.toString());
                }
                //Encolar en Cola Preferencial
                if (asignarCola >= 21 && asignarCola <= 30) {
                    colaPreferencial.encolar(objCliente);
                    txtArea.setText(
                            "Cola asignada: Preferencial" + "\n"
                            + objCliente.toString());
                }

                // Programa la próxima ejecución con un periodo aleatorio
                int delay = 1 + new java.util.Random().nextInt(15); // Delay entre 1 y 10 segundos
                executor.schedule(this, delay, TimeUnit.SECONDS);
            }
        };

        executor.schedule(task, 0, TimeUnit.SECONDS);

    }

    @FXML
    public void finalizar(ActionEvent event) {
        if (executor != null) {
            executor.shutdown();
            txtArea.setText("Programa Finalizado");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colaNatural = new Cola<>();
        colaClientes = new Cola<>();
        colaPreferencial = new Cola<>();
        random = new Random();
    }

}
