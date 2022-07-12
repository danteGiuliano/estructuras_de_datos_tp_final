package ABM;

import java.io.Console;
import java.util.Scanner;

import Dominio.Aeropuerto;
import Estructuras.Grafo.Grafo;
import ORM.ORM;

public class AeropuertoABM {

    static String format = "%20s %60s";
    static Scanner edat = new Scanner(System.in);

    static String telefono_valido = "^[0-9]{3}+[-]+[0-9]{7}$";// Formato xxx-xxxxxxx
    static String nombre_valido = "^([a-zA-Z]+[ ]*){17}$"; // Formato de nombres
    static String codigo_valido = "^[A-Z]{3}$"; // Formato XXX

    static Grafo mapa_aeroportuario = new Grafo();

    private static void cargar_mapa() {
        mapa_aeroportuario = ORM.get_aeropuertos();
    }

    public static void main(String[] args) {
        cargar_mapa();
        menu();
        System.out.println("Programa terminado");
    }

    public static void menu() {

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out.println("|                                 Aeropuerto Menu                                   |");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(format, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(format, "1 |", "Cargar Aeropuerto");
            System.out.println("");
            System.out.format(format, "2 |", "Modificar Aeropuerto");
            System.out.println("");
            System.out.format(format, "3 |", "Borrar Aeropuerto");
            System.out.println("");
            System.out.format(format, "4 |", "Menu de Rutas");
            System.out.println("");
            System.out.format(format, "5 |", "Mostrar Aeropuertos");
            System.out.println("");
            System.out.format(format, "6 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (opcion(edat.nextLine()));
    }

    public static boolean opcion(String opcion) {
        boolean sesion = true;
        int opcion_numerica = 0;
        try {
            opcion_numerica = Integer.parseInt(opcion);
            switch (opcion_numerica) {
                case 1:
                    opcion_carga_aeropuerto();
                    break;
                case 2:
                    opcion_modificar_aeropuerto();
                    break;
                case 3:opcion_borrar_aeropuerto();
                    break;
                case 4:opcion_rutas();
                    break;
                case 5:
                    mostrar_aeropuertos();
                    break;
                case 6:
                    sesion = false;
                    break;
                default:
                    System.out.println("Error de opcion ingrese una opcion valida");
                    break;
            }
        } catch (NumberFormatException e) {
           System.out.println("");
        }
        return sesion;
    }

    /**
     * ________________________________________________________________________________________________
     * | |
     * | |
     * | |
     * | |
     * | |
     * |_______________________________________________________________________________________________
     */

    private static void opcion_carga_aeropuerto() {
        String nombre_aeropuerto = "";
        String codigo_aeropuerto = "";
        String numero_telefono = "";

        nombre_aeropuerto = validar_nombre();
        codigo_aeropuerto = validar_codigo();
        numero_telefono = validar_codigo();

        mapa_aeroportuario.insertar_vertice(new Aeropuerto(codigo_aeropuerto, nombre_aeropuerto, numero_telefono));
    }

    /**
     * _________________________________________________________________________________________________
     * | |
     * | |
     * | |
     * | |
     * | |
     * |_______________________________________________________________________________________________|
     */

    private static void opcion_modificar_aeropuerto() {
        String lectura = "";
        lectura = validar_codigo();

        Aeropuerto aeropuerto = (Aeropuerto) mapa_aeroportuario.extraer_vertice(lectura);
        do {
            System.out.println("                         Aeropuerto " + aeropuerto.get_nombre());
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(format, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(format, "1 |", "Modificar Codigo");
            System.out.println("");
            System.out.format(format, "2 |", "Modificar Nombre");
            System.out.println("");
            System.out.format(format, "3 |", "Modificar Telefono");
            System.out.println("");
            System.out.format(format, "4 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (opciones_modificar(edat.nextLine(), aeropuerto));
    }

    private static boolean opciones_modificar(String opcion, Aeropuerto aeropuerto) {
        boolean sesion = true;
        int opcion_numerica = 0;
        try {
            opcion_numerica = Integer.parseInt(opcion);
            switch (opcion_numerica) {
                case 1:
                    String codigo = validar_codigo();
                    aeropuerto.set_codigo(codigo);
                    System.out.println("CODIGO MODIFICADO CON EXITO!");
                    break;
                case 2:
                    String nombre = validar_nombre();
                    aeropuerto.set_nombre(nombre);
                    System.out.println("NOMBRE MODIFICADO CON EXITO!");
                    break;
                case 3:
                    String telefono= validar_telefono();
                    aeropuerto.set_telefono(telefono);
                    System.out.println("TELEFONO MODIFICADO CON EXITO!");
                    break;
                case 4:
                    sesion = false;
                    break;
                default:
                    System.out.println("Error de opcion ingrese una opcion valida");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("");
        }
        return sesion;
    }
     /**
     * _________________________________________________________________________________________________
     * | |
     * | |
     * | |
     * | |
     * | |
     * |_______________________________________________________________________________________________|
     */

    private static void opcion_borrar_aeropuerto() {
        String codigo = validar_codigo();
        try {
            Aeropuerto aeropuerto =(Aeropuerto)mapa_aeroportuario.extraer_vertice(codigo);
            if(aeropuerto!=null){
                System.out.println("Esta seguro de que quiere borrar \n"+aeropuerto.info_aeropuerto());
                if(edat.nextBoolean()){
                    mapa_aeroportuario.eliminar_vertice(codigo);
                }
            }    
        } catch (Exception e) {
            System.out.println("Ha ocurrido un fallo, intente con otro codigo.");
            opcion_borrar_aeropuerto();
        }
        
    }
    /**
     * _________________________________________________________________________________________________
     * | |
     * | |
     * | |
     * | |
     * | |
     * |_______________________________________________________________________________________________|
     */

    private static void opcion_rutas() {
        System.out.println(mapa_aeroportuario.toString());
    }

    /**
     * _________________________________________________________________________________________________
     * | |
     * | |
     * | |
     * | |
     * | |
     * |_______________________________________________________________________________________________|
     */

    private static void mostrar_aeropuertos() {
        System.out.println(mapa_aeroportuario.toString());
    }

    /**
     * Validadores
     * 
     */

    private static String validar_nombre() {
        String nombre_aeropuerto = "";
        boolean aceptado = false;

        while (!aceptado) {
            System.out.println("Ingrese el nombre de su Aeropuerto");
            nombre_aeropuerto = edat.nextLine();
            if (!nombre_aeropuerto.matches(nombre_valido)) {
                System.out.println("Su nombre no tiene un formato valido ingrese un nombre valido");
            } else {
                aceptado = true;
            }
        }
        return nombre_aeropuerto;
    }

    private static String validar_codigo() {
        String codigo_aeropuerto = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese su codigo Aeroportuario 3 letras ej : NQN");
            codigo_aeropuerto = edat.nextLine();
            if (!codigo_aeropuerto.matches(codigo_valido)) {
                System.out.println("Formato invalido");
            } else {
                aceptado = true;
            }
        }
        return codigo_aeropuerto;
    }

    private static String validar_telefono() {
        String numero_telefono = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese su numero de telefono");
            numero_telefono = edat.nextLine();
            if (!numero_telefono.matches(telefono_valido)) {
                System.out.println("Formato de telefono invalido debe ser xxx-xxxxxxx");
            } else {
                aceptado = true;
            }
        }
        return numero_telefono;
    }

}