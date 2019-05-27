/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ex01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author infor04
 */
public class Ex01 {

    // Constantes del programa
    private final static String user = "alumne";
    private final static String password = "alualualu";
    private final static String database = "jdbc:mysql://192.168.56.101:3306/beer";
    private final static String f_salida = "C:\\Users\\infor04\\Desktop\\consultas.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try (Connection con = DriverManager.getConnection(database, user, password)) {

            boolean salir = false;
            while (!salir) { // Bucle hasta que el usuario decida salir

                switch (pedirOpcion()) {
                    case 1:
                        insertar_serves(con);
                        break;
                    case 2:
                        consultar_drinker(con);
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }

            }
        } catch (SQLException ex) {
            System.out.println("Error en la bbdd");
            System.out.println(ex.getSQLState());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error no controlado");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    // Muestra el menú por pantalla y pide que el usaurio introduzca una opción
    public static int pedirOpcion() {
        Scanner lector = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("1) Insertar SERVES");
        System.out.println("2) Consultar DRINKER");
        System.out.println("3) Salir");
        System.out.println("--------------------------------");
        System.out.print("Introduce una opción: ");
        return lector.nextInt();
    }

    public static void consultar_drinker(Connection con) throws IOException, SQLException {
        try (BufferedWriter escritorBuffer = new BufferedWriter(new FileWriter(f_salida));
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from Drinker")) { // select de toda la tabla

            System.out.println("\nEn la tabla DRINKER tenemos los siguiente datos:");
            System.out.println("=======================================================");
            escritorBuffer.append("En la tabla DRINKER tenemos los siguiente datos:");
            escritorBuffer.newLine();
            escritorBuffer.append("=======================================================");
            escritorBuffer.newLine();
            while (rs.next()) { // mostramos los resultados de cada fila y los guardamos en un fichero de texto
                System.out.println("Nombre del cliente: " + rs.getString(1));
                escritorBuffer.append("Nombre del cliente: " + rs.getString(1));
                escritorBuffer.newLine();
                System.out.println("   Dirección: " + rs.getString(2) + "\n");
                escritorBuffer.append("   Dirección: " + rs.getString(2));
                escritorBuffer.newLine();
            }
        }
    }

    public static void insertar_serves(Connection con) throws SQLException {
        Scanner lector = new Scanner(System.in);
        System.out.print("Introduzca el nº de registros a insertar: ");
        int n = 0; // incializamos el valor introducido a 0
        do {
            n = lector.nextInt();
            lector.nextLine();
            if (n < 3 || n % 3 != 0) {
                System.out.println("Debe introducir un valor múltiplo de 3.");
                System.out.print("Vuelva a introducir el nº de registros: ");
            }
        } while (n < 3 || n % 3 != 0); // seguiremos pidiendo que se introduzca un valor hasta que sea múltiplo de 3 

        try (PreparedStatement pst = con.prepareStatement("insert into Serves values (?, ?, ?)")) { // Prepared Statement INSERT
            boolean estado_previo = con.getAutoCommit();
            try {
                con.setAutoCommit(false); // inicio transacción
                Savepoint save = null;
                int i = 0;
                while (i < (n / 3)) { // Bucle de n/3 veces (registros a insertar de 3 en 3)
                    int j = 0;
                    try {
                        while (j < 3) { // insertar 3 registros

                            System.out.println("### Inserta registro " + (j + 1) + "/3 de grupo " + (i + 1) + "/" + (n / 3) + " ###");
                            System.out.print("Nombre del bar: ");
                            pst.setString(1, lector.nextLine());
                            System.out.print("Nombre de la cerveza: ");
                            pst.setString(2, lector.nextLine());
                            System.out.print("Precio: ");
                            pst.setFloat(3, lector.nextFloat());
                            lector.nextLine();
                            pst.executeUpdate();
                            j++;
                        }
                        save = con.setSavepoint(); // al finalizar 3 inserts guardamos un savepoint
                        i++;
                        System.out.println("Grupo " + (i + 1) + "/" + (n / 3) + " finalizado");
                    } catch (Exception ex) { // capturamos cualquier excepción
                        System.out.println("Error insertando datos");
                        System.out.println("Vuelve a insertar desde el principio del grupo " + (i + 1) + "/" + (n / 3));
                        con.rollback(save);
                    }
                }
                con.commit(); // fin de la transacción
                System.out.println("### Fin inserción ###");
            } catch (Exception ex) {
                con.rollback();
                System.out.println("Transacción abortada");
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            } finally {
                //con.setAutoCommit(true);
                con.setAutoCommit(estado_previo); // devolvemos el autocommit al estado previo
            }
        }

    }

}
