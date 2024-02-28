/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package cajerosbanco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase CajerosBanco para iniciar la interfaz gráfica
 *
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Romero Código 2225751
 * @date 24 Febrero 2024
 * @version 1.0
 */
public class CajerosBanco extends Application {

    /**
     * Inicia la interfaz gráfica señalada
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Inicia los parámetros
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
