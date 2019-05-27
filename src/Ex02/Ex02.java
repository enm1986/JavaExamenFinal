/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ex02;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author infor04
 */
public class Ex02 {

    static int MAXPLAZAS = 10; // plazas a generar automáticamente
    static int MAXPERSONAS = 9;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ArrayList<Plazas> lista_plazas = new ArrayList<>();
        ArrayList<Persona> lista_personas = new ArrayList<>();
        /*
        // Plazas (int id, char tipo)
        lista_plazas.add(new Plazas(0, 'D'));
        lista_plazas.add(new Plazas(1, 'S'));
        lista_plazas.add(new Plazas(2, 'D'));
        lista_plazas.add(new Plazas(3, 'S'));
        lista_plazas.add(new Plazas(4, 'D'));
        lista_plazas.add(new Plazas(5, 'S'));
        lista_plazas.add(new Plazas(6, 'D'));
        lista_plazas.add(new Plazas(7, 'S'));
        lista_plazas.add(new Plazas(8, 'D'));
        lista_plazas.add(new Plazas(9, 'S'));
        //Docente(String titulacion, float puntos, String nombre, String apellido1, String apellido2)
        //Sanitario(String especialidad, int dias_trabajados, String nombre, String apellido1, String apellido2)
        lista_personas.add(new Docente("T1", 4, "ND1", "A1D1", "A2D1"));
        lista_personas.add(new Sanitario("S1", 40, "NS1", "A1S1", "A2S1"));
        lista_personas.add(new Docente("T2", 6, "ND2", "A1D2", "A2D2"));
        lista_personas.add(new Docente("T3", 3, "ND3", "A1D3", "A2D3"));
        lista_personas.add(new Sanitario("S2", 25, "NS2", "A1S2", "A2S2"));
        */
        simularAdjudicacion(lista_plazas, lista_personas);
        adjudicarPlazas(lista_plazas, lista_personas);
        mostrarAdjudicaciones(lista_plazas);

    }

    public static void adjudicarPlazas(ArrayList<Plazas> plazas, ArrayList<Persona> personas) {
        int tmp;
        int i = 0;
        while (i < plazas.size() && personas.size() > 0) { // mientras haya plazas y personas disponibles
            switch (plazas.get(i).getTipo()) {
                case 'D': // plaza Docente
                    System.out.println("D");
                    tmp = -1;
                    for (int j = 0; j < personas.size(); j++) { // recorremos la lista de personas
                        if (personas.get(j) instanceof Docente) {
                            if (tmp == -1) { // primera instancia de Docente encontrada
                                tmp = j; // guardamos la posición de la primera instancia de Docente en personas
                            }
                            if (((Docente) personas.get(j)).getPuntos() > ((Docente) personas.get(tmp)).getPuntos()) {
                                tmp = j; // guardamos el que más Puntos tiene
                            }
                        }
                    }
                    if (personas.get(tmp) instanceof Docente) { // adjudicamos la plaza
                        plazas.get(i).setInterino(personas.get(tmp));
                        plazas.get(i).setAdjudicada(true);
                        personas.remove(tmp); // eliminamos de la lista de personas si se le adjdica una plaza
                    }
                    break;
                case 'S': // plaza Sanitario
                    System.out.println("S");
                    tmp = -1;
                    for (int j = 0; j < personas.size(); j++) {
                        if (personas.get(j) instanceof Sanitario) {
                            if (tmp == -1) { // primera instancia de Sanitario encontrada
                                tmp = j; // guardamos la posición de la primera instancia de Sanitario en personas
                            }
                            if (((Sanitario) personas.get(j)).getDias_trabajados() > ((Sanitario) personas.get(tmp)).getDias_trabajados()) {
                                tmp = j; // guardamos el que más experiencia tiene
                            }
                        }
                    }
                    if (personas.get(tmp) instanceof Sanitario) { // adjudicamos la plaza
                        plazas.get(i).setInterino(personas.get(tmp));
                        plazas.get(i).setAdjudicada(true);
                        personas.remove(tmp); // eliminamos de la lista de personas si se le adjdica una plaza
                    }
                    break;
            }
            i++;
        }
    }

    public static void mostrarAdjudicaciones(ArrayList<Plazas> plazas) {
        System.out.println("=========================================");
        System.out.println("Plazas para docentes actualizadas:");
        System.out.println("=========================================");
        for (int i = 0; i < plazas.size(); i++) {
            if (plazas.get(i).getTipo() == 'D') {
                plazas.get(i).mostrarPlaza();
            }
        }
        System.out.println("\n=========================================");
        System.out.println("Plazas para sanitarios actualizadas:");
        System.out.println("=========================================");
        for (int i = 0; i < plazas.size(); i++) {
            if (plazas.get(i).getTipo() == 'S') {
                plazas.get(i).mostrarPlaza();
            }
        }
    }

    public static void simularAdjudicacion(ArrayList<Plazas> plazas, ArrayList<Persona> personas) {
        char C;
        for (int i = 0; i < MAXPLAZAS; i++) { // creamos las plazas
            if (i % 2 == 0) {
                C = 'D';
            } else {
                C = 'S';
            }
            plazas.add(new Plazas(i, C));
           
        }

        for (int i = 0; i < MAXPERSONAS; i++) { // creamos personas
            if (i % 2 == 0) {
                personas.add(new Docente(("titulacion"+i), (i*2), ("nombre"+i), ("apellido1"+i), ("apellido2"+i)));
            } else {
                personas.add(new Sanitario(("especialidad"+i), (i*2), ("nombre"+i), ("apellido1"+i), ("apellido2"+i)));
            }
           
        }
    }
}
