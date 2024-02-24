/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import cola.Base;

/*/** 
 * Clase Cliente para la creación de los clientes a asignar a las colas
 * @author andres_gab.fernandez@uao.edu.co Andrés Gabriel Fernández Código 2225751
 * @author valeria.garcia_perez@uao.edu.co Valeria García Pérez Código 2225662
 * @author juan_p.gutierrez@uao.edu.co Juan Pablo Gutierrez Código 2221673
 * @date 24 Febrero 2024
 * @version 1.0 
 */
public class Cliente extends Base {

    private int edad;
    private int tiempoAtencion;

    public Cliente() {
    }

    public Cliente(int edad, int tiempoAtencion) {
        this.edad = edad;
        this.tiempoAtencion = tiempoAtencion;
    }

    /**
     * Toma el valor de tiempoAtencion
     *
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
     * Muestra en formato de cadena la información del objeto de la clase
     * Cliente
     *
     * @return la información del objeto de la clase Cliente como una cadena
     */
    @Override
    public String toString() {
        return "Cliente{" + "edad=" + edad + ", tiempoAtencion=" + tiempoAtencion + '}';
    }

    /**
     * Implementa el método abstracto de Base
     *
     * @param edad nuevo valor de edad
     */
    @Override
    public Base copy() {
        return new Cliente(edad, tiempoAtencion);
    }

}
