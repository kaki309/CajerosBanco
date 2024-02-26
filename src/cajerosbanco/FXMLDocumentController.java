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
import java.util.concurrent.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.Cajero;

/*/** 
 * Clase FXML Controller para manejar la interfaz gráfica
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Código 2225751
 * @author valeria.garcia_perez@uao.edu.co Valeria García Pérez Código 2225662
 * @author juan_p.gutierrez@uao.edu.co Juan Pablo Gutierrez Código 2221673
 * @date 24 Febrero 2024
 * @version 1.0 
 */
public class FXMLDocumentController implements Initializable {

    //Variables de interfaz gráfica
    @FXML
    private TextArea txtColaNatural;
    @FXML
    private TextArea txtColaClientes;
    @FXML
    private TextArea txtColaPreferencial;

    //Variables de funcionamiento
    Cola<Cliente> colaNatural;
    Cola<Cliente> colaClientes;
    Cola<Cliente> colaPreferencial;
    Random random;
    LinkedList<Cliente> clientesAtendidos;
    private ScheduledExecutorService executor;
    private Future<?> futureTask;

    //METODOS
    @FXML
    public void iniciar(ActionEvent event) {
        crearClientes();

        //PARA DEMOSTRAR QUE LOS CAJEROS ESTÁN OCUPADOS, PONER COLOR EN ROJO 
        //SI ESTÁN LIBRES DEBERÍAN SER VERDES
        
    }

    public void crearClientes() {

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
                    txtColaNatural.setText(mostrar(colaNatural));
                }
                //Encolar en Cola para Clientes
                if (asignarCola >= 11 && asignarCola <= 20) {
                    colaClientes.encolar(objCliente);
                    txtColaClientes.setText(mostrar(colaClientes));
                }
                //Encolar en Cola Preferencial
                if (asignarCola >= 21 && asignarCola <= 30) {
                    colaPreferencial.encolar(objCliente);
                    txtColaPreferencial.setText(mostrar(colaPreferencial));
                }

                // Programa la próxima ejecución con un periodo aleatorio
                int delay = 3 + new java.util.Random().nextInt(13); // Delay entre 1 y 15 segundos
                futureTask = executor.schedule(this, delay, TimeUnit.SECONDS);
            }
        };
        futureTask = executor.schedule(task, 0, TimeUnit.SECONDS);

    }
    
    
    public String mostrar(Cola<Cliente> cola) {

        String linea = cola.toString();
        String[] info;
        String cadena = "";

        info = linea.split("seg.");

        for (int i = 0; i < info.length; i++) {

            cadena += info[i] + "\n";
        }
        return cadena;
    }

    @FXML
    public void finalizar(ActionEvent event) {
        if (futureTask != null) {
            futureTask.cancel(true);
        }
        if (executor != null) {
            executor.shutdown();
            txtColaNatural.setText("Programa Finalizado");
            txtColaClientes.setText("Programa Finalizado");
            txtColaPreferencial.setText("Programa Finalizado");
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
