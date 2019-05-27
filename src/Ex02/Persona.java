/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ex02;

/**
 *
 * @author navar
 */
public class Persona {

    private static int contador = 1;
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;

    public Persona() {
    }

    public Persona(String nombre, String apellido1, String apellido2) {
        this.setId();
        this.setNombre(nombre);
        this.setApellido1(apellido1);
        this.setApellido2(apellido2);
    }

    public Persona(Persona p) {
        this.setId();
        this.setNombre(p.nombre);
        this.setApellido1(p.apellido1);
        this.setApellido2(p.apellido2);
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Persona.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = contador++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + this.getId());
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Apellido 1: " + this.getApellido1());
        System.out.println("Apellido 2: " + this.getApellido2());
    }

}
