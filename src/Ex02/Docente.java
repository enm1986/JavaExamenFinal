/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ex02;

/**
 *
 * @author infor04
 */
public class Docente extends Persona {

    //Atributos
    private String titulacion;
    private float puntos;

    //Constructor vacio
    public Docente() {
    }

    // Constructor con parametros
    public Docente(String titulacion, float puntos, String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2);
        this.setTitulacion(titulacion);
        this.setPuntos(puntos);
    }

    //Constructor copia
    public Docente(Docente d) {
        super(d);
        this.setTitulacion(d.titulacion);
        this.setPuntos(d.puntos);
    }

    //Getters y Setters
    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Titulación: " + this.getTitulacion());
        System.out.println("Puntos: " + this.getPuntos());
    }

}
