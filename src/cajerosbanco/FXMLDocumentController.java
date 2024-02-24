/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cajerosbanco;

import cola.Cola;
import modelo.Cliente;
import modelo.Cliente;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;

/**
 *
 * @author andre
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Circle cajero1;

    Cola<Cliente> colaNatural;
    Cola<Cliente> colaClientes;
    Cola<Cliente> colaPreferencial;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Cola colaNatural = new Cola<>();
        Cola colaClientes = new Cola<>();
        Cola colaPreferencial = new Cola<>();

    }

}
