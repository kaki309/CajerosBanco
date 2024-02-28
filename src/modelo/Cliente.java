/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Base;

/*/** 
 * Clase Cliente, para la creación de los obj Clientes a asignar a las colas
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Romero Código 2225751
 * @date 24 Febrero 2024
 * @version 1.0 
 */
public class Cliente extends Base {

    private int edad;
    private int tiempoAtencion;

    /**
     * Constructor vacio para los objetos de Cliente
     */
    public Cliente() {
    }

    /**
     * Constructor para los objetos de Cliente
     *
     * @param tiempoAtencion valor para asignarle al tiempoAtencion del obj
     * @param edad valor para asignarle a la edad del obj
     */
    public Cliente(int edad, int tiempoAtencion) {
        this.edad = edad;
        this.tiempoAtencion = tiempoAtencion;
    }

    /**
     * Toma el valor de tiempoAtencion
     *
     * @return el valor de tiempoAtencion
     */
    public int getTiempoAtencion() {
        return tiempoAtencion;
    }

    /**
     * Establece el valor de tiempoAtencion
     *
     * @param tiempoAtencion nuevo valor de tiempoAtencion
     */
    public void setTiempoAtencion(int tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    /**
     * Toma el valor de edad
     *
     * @return el valor de edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece el valor de edad
     *
     * @param edad nuevo valor de edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Muestra en forma de cadena la información del objeto tipo Cliente
     *
     * @return la información del objeto de la clase Cliente como una cadena
     */
    @Override
    public String toString() {
        return "Edad: " + edad + ", tiempo: " + tiempoAtencion + "seg.";
    }

    /**
     * Implementa el método abstracto de Base
     *
     * @return una copia del objeto tipo Cliente
     */
    @Override
    public Base copy() {
        return new Cliente(edad, tiempoAtencion);
    }

}
