package ABM;

import java.util.Scanner;

import Dominio.Vuelo;
import Estructuras.AVL.ArbolAVL;
import LOG.Logger;
import ORM.ORM;
import Validadores.Validador;

public class VueloABM {

    static String formato_menu = "%20s %60s";
    static String formato_vuelo = "%10s %20s %20s %15s %15s";

    static Scanner edat = new Scanner(System.in);

    public static ArbolAVL vuelos = ORM.get_vuelos();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("|                                 Vuelo Menu                                        |");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Cargar Vuelo");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Seleccionar Vuelo");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "volver atras");
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
                    cargar_vuelo();
                    break;
                case 2:
                    seleccionar_vuelo();
                    break;
                case 3:
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

    private static void seleccionar_vuelo() {
        Vuelo vuelo = (Vuelo) vuelos.extraer_elemento(Validador.validar_vuelo());

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("                  Vuelo " + vuelo.get_codigo());
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Ver informacion Vuelo");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Eliminar Vuelo");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "Modificar Hora Inicio");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (opcion_seleccionar(edat.nextLine(), vuelo));

    }

    private static boolean opcion_seleccionar(String opcion, Vuelo vuelo) {
        boolean sesion = true;
        int opcion_numerica = 0;
        try {
            opcion_numerica = Integer.parseInt(opcion);
            switch (opcion_numerica) {
                case 1:
                    ver_informacion_vuelo(vuelo);
                    break;
                case 2:
                    System.out.println("Esta seguro que quiere borrar el vuelo?");
                    if (Validador.validar_opcion()) {
                        vuelos.eliminar(vuelo);
                        System.out.println("VUELO BORRADO CON EXITO");
                        Logger.Vuelo_baja(vuelo);
                        sesion = false;
                    }
                    break;
                case 3:
                    String hora = Validador.validar_hora();
                    vuelo.set_hora_llegada(hora);
                    System.out.println("HORA DE INICIO MODIFICADA");
                    Logger.Vuelo_modificacion(vuelo);
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

    public static void ver_informacion_vuelo(Vuelo vuelo) {
        String codigo = vuelo.get_codigo();
        String aeropuerto_origen = vuelo.get_aeropuerto_origen();
        String aeropuerto_destino = vuelo.get_aeropuerto_destino();
        String hora_salida = vuelo.get_hora_salida();
        String hora_llegada = vuelo.get_hora_llegada();

        System.out.println(
                "------------------------------------------------------------------------------------");
        System.out.printf(formato_vuelo, "CODIGO", "AEROPUERTO ORIGEN", "AEROPUERTO DESTINO", "HORA SALIDA",
                "HORA LLEGADA");
        System.out.println();
        System.out.println(
                "------------------------------------------------------------------------------------");
        System.out.format(formato_vuelo, codigo, aeropuerto_origen, aeropuerto_destino, hora_salida, hora_llegada);
        System.out.println("");

    }

    public static void cargar_vuelo() {
        String codigo = Validador.validar_vuelo();
        System.out.println("Ingrese el aeropuerto de inicio");
        String aeropuerto_origen = Validador.validar_aeropuerto();
        String aeropuerto_destino = Validador.validar_aeropuerto();
        System.out.println("Ingrese hora de inicio");
        String hora_salida = Validador.validar_hora();
        String hora_llegada = Validador.validar_hora();

        Vuelo vuelo = new Vuelo(hora_llegada, codigo, aeropuerto_destino, hora_salida, aeropuerto_origen);

        if (vuelos.insertar(vuelo)) {
            System.out.println("VUELO AÑADIDO CON EXITO!");
            Logger.Vuelo_alta(vuelo);
        }

    }
}