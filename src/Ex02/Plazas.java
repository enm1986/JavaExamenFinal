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
public class Plazas {

    // Atributos
    private int id;
    private char tipo;
    private boolean adjudicada;
    private Persona interino;

    // constructor vacio
    public Plazas() {
    }

    // Constructor con parametros
    public Plazas(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
        this.adjudicada = false;
        this.interino = null;
    }

    // G&S
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean isAdjudicada() {
        return adjudicada;
    }

    public void setAdjudicada(boolean adjudicada) {
        this.adjudicada = adjudicada;
    }

    public Persona getInterino() {
        return interino;
    }

    public void setInterino(Persona interino) {
        this.interino = interino;
    }
    
    //Metodos
    
   public void mostrarPlaza(){
       System.out.println("ID plaza: " + this.getId());
       System.out.println("Tipo: "+ this.getTipo());
       if (this.isAdjudicada()){
           System.out.println("ADJUDICADA");
           this.getInterino().mostrarDatos();
       } else{
           System.out.println("NO ADJUDICADA");
       }
       System.out.println("");
   }

}
