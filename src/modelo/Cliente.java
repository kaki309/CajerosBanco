/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Base;

/*/** 
 * Clase Cliente para la creación de los clientes a asignar a las colas
 * @author andres_gab.fernandez@uao.edu.co Andrés Fernández Código 2225751
 * @date 24 Febrero 2024
 * @version 1.0 
 */ 
public class Cliente extends Base {

    private String edad;
    private double tiempoAtencion;

    public Cliente() {
    }

    public Cliente(String edad, double tiempoAtencion) {
        this.edad = edad;
        this.tiempoAtencion = tiempoAtencion;
    }
    
    /**
     * Toma el valor de tiempoAtencion
     *
     * 
     * @return el valor de tiempoAtencion
     */
    public double getTiempoAtencion() {
        return tiempoAtencion;
    }

    /**
     * Establece el valor de tiempoAtencion
     *
     * @param tiempoAtencion nuevo valor de tiempoAtencion
     */
    public void setTiempoAtencion(double tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    /**
     * Toma el valor de edad
     *
     * @return el valor de edad
     */
    public String getEdad() {
        return edad;
    }

    /**
     * Establece el valor de edad
     *
     * @param edad nuevo valor de edad
     */
    public void setEdad(String edad) {
        this.edad = edad;
    }

    
    /**
     * Muestra en formato de cadena la información del objeto de la clase Cliente
     *
     * @return la información del objeto de la clase Cliente como una cadena
     */
    @Override
    public String toString() {
        return "Cliente{" + "edad=" + edad + ", tiempoAtencion=" + tiempoAtencion + '}';
    }
    
    /**
     *Implementa el método abstracto de Base
     *
     * @param edad nuevo valor de edad
     */
    @Override
    public Base copy() {
        return new Cliente(edad, tiempoAtencion);
    }

}
