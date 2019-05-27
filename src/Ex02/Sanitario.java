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
public class Sanitario extends Persona {

    //Atributos
    private String especialidad;
    private int dias_trabajados;

    //Constructor vacio
    public Sanitario() {
    }

    // Constructor con parametros
    public Sanitario(String especialidad, int dias_trabajados, String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2);
        this.setEspecialidad(especialidad);
        this.setDias_trabajados(dias_trabajados);
    }

    // Constructor copia
    public Sanitario(Sanitario s) {
        super(s);
        this.setEspecialidad(s.especialidad);
        this.setDias_trabajados(s.dias_trabajados);
    }

    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getDias_trabajados() {
        return dias_trabajados;
    }

    public void setDias_trabajados(int dias_trabajados) {
        this.dias_trabajados = dias_trabajados;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Especialidad: " + this.getEspecialidad());
        System.out.println("Experiencia: " + this.getDias_trabajados() + " días");
    }

}
