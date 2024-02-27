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
import java.util.concurrent.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
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

    //Variables de funcionamiento
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
    private ScheduledExecutorService executor;
    private Future<?> futureTask;

    //METODOS
    @FXML
    public void iniciar(ActionEvent event) {
        crearClientes();

//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            public void run() {
//
//                cajero1.run(colaNatural, colaClientes, colaPreferencial, idCircle1, textCajero1);
//
//            }
//        };
//        timer.schedule(task, 0);
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
                int colaMax = 3;

                int edadAleatoria = random.nextInt((edadMaxima - edadMinima) + 1) + edadMinima;
                int tiempoAleatorio = random.nextInt((tiempoMaximo - tiempoMinimo) + 1) + tiempoMinimo;
                Cliente objCliente = new Cliente(edadAleatoria, tiempoAleatorio);

                int asignarCola = random.nextInt((colaMax - colaMin) + 1) + colaMin;

                //Encolar Natural      
                if (asignarCola == 1) {
                    colaNatural.encolar(objCliente);
                    txtColaNatural.setText(mostrar(colaNatural));
                }
                //Encolar Clientes
                if (asignarCola == 2) {
                    colaClientes.encolar(objCliente);
                    txtColaClientes.setText(mostrar(colaClientes));
                }
                //Encolar Preferencial
                if (asignarCola == 3) {
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

    public void activar() {

        cajero1.run(colaNatural, idCircle1, textCajero1);
        cajero2.run(colaNatural, idCircle2, textCajero2);

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
        cajero1 = new Cajero();
        idCircle1.setStroke(Color.DODGERBLUE);
        cajero2 = new Cajero();
        idCircle2.setStroke(Color.DODGERBLUE);
        cajero3 = new Cajero();
        idCircle3.setStroke(Color.DODGERBLUE);
        cajero4 = new Cajero();
        idCircle4.setStroke(Color.DODGERBLUE);
        cajero5 = new Cajero();
        idCircle5.setStroke(Color.DODGERBLUE);
        cajero6 = new Cajero();
        idCircle6.setStroke(Color.DODGERBLUE);

    }

}
