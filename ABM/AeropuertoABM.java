package ABM;

import java.util.Scanner;


import Dominio.Aeropuerto;
import Estructuras.Grafo.Grafo;
import Estructuras.Lista.Lista;
import ORM.ORM;

public class AeropuertoABM {

    static String format = "%20s %60s";
    static Scanner edat = new Scanner(System.in);

    static String telefono_valido = "^[0-9]{3}+[-]+[0-9]{7}$";// Formato xxx-xxxxxxx
    static String nombre_valido = "^([a-zA-Z]+[ ]*){17}$"; // Formato de nombres
    static String codigo_valido = "^[A-Z]{3}$"; // Formato XXX

    static Grafo mapa_aeroportuario = ORM.get_aeropuertos();


    public static void main(String[] args) {
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
                case 3:
                    opcion_borrar_aeropuerto();
                    break;
                case 4:
                    opcion_rutas();
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
                    String telefono = validar_telefono();
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
            Aeropuerto aeropuerto = (Aeropuerto) mapa_aeroportuario.extraer_vertice(codigo);
            if (aeropuerto != null) {
                System.out.println("Esta seguro de que quiere borrar \n" + aeropuerto.info_aeropuerto());
                if (edat.nextBoolean()) {
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
        do {
            System.out.println("                                      Menu Rutas ");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(format, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(format, "1 |", "Agregar Ruta");
            System.out.println("");
            System.out.format(format, "2 |", "Modificar Ruta");
            System.out.println("");
            System.out.format(format, "3 |", "Eliminar  Ruta");
            System.out.println("");
            System.out.format(format, "4 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (opciones_rutas(edat.nextLine()));
    }

    private static boolean opciones_rutas(String opcion) {
        boolean sesion = true;
        int opcion_numerica = 0;
        try {
            opcion_numerica = Integer.parseInt(opcion);
            switch (opcion_numerica) {
                case 1:
                    agregar_ruta();
                    break;
                case 2:
                    modificar_ruta();
                    break;
                case 3:
                    borrar_ruta();
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

    private static void agregar_ruta() {
        String aeropuerto_a = validar_aeropuerto();
        String aeropuerto_b = validar_aeropuerto();
        String ruta = validar_ruta();

        mapa_aeroportuario.insertar_arco(aeropuerto_a, aeropuerto_b, ruta);
        System.out.println("Nueva Ruta añadida");
    }

    private static void borrar_ruta() {

        String aeropuerto_a = validar_aeropuerto();
        String aeropuerto_b = validar_aeropuerto();

        String ruta = validar_ruta();
        if (mapa_aeroportuario.eliminar_arco(aeropuerto_a, aeropuerto_b, ruta)) {
            System.out.println("Ruta Eliminada");
        }
        {
            System.out.println("Ruta no eliminada");
        }

    }

    private static void modificar_ruta() {
        int opcion = 0;
        boolean sesion=true;
        System.out.println("Seleccione los aeropuertos para ver las rutas disponibles");
        String aeropuerto_a = validar_aeropuerto();
        String aeropuerto_b = validar_aeropuerto();
        Lista rutas = mapa_aeroportuario.listar_arcos(aeropuerto_a, aeropuerto_b);
        while(sesion){

        System.out.println("Rutas:");
        for (int i = 1; i <= rutas.longitud(); i++) {
            System.out.print("[ " + i + " -> " + rutas.recuperar(i) + " ],");
        }
        System.out.println("Seleccione una ruta a modificar 0 para salir");
        opcion = validar_numero();
        if (opcion != 0) {
            int distancia = 0;
            String ruta = (String) rutas.recuperar(opcion);
            System.out.println("Selecciono la ruta de :" + ruta + " hrs");
            System.out.println("Ingrese una nueva distancia , 0 para cancelar");
            distancia = validar_numero();
            if (distancia != 0) {
                mapa_aeroportuario.eliminar_arco(aeropuerto_a, aeropuerto_b, ruta);
                mapa_aeroportuario.insertar_arco(aeropuerto_a, aeropuerto_b, String.valueOf(distancia));
                System.out.println("RUTA MODIFICADA CON EXITO");
            }

        }else{
            sesion=false;
        }
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

    private static String validar_aeropuerto() {
        String aeropuerto = "";
        boolean aceptado = false;
        while (!aceptado) {
            aeropuerto = validar_codigo();
            if (!mapa_aeroportuario.existe_vertice(aeropuerto)) {
                System.out.println("No existe Aeropuerto");
            } else {
                aceptado = true;
            }
        }
        return aeropuerto;
    }

    private static String validar_ruta() {
        boolean ruta_valida = false;
        String distancia = "";
        String numeros = "^[0-9]*$";

        while (!ruta_valida) {
            System.out.println("Ingrese la distancia en horas");
            distancia = edat.nextLine();
            if (distancia == "" || !distancia.matches(numeros)) {
                System.out.println("Formato invalido ");
            } else {
                ruta_valida = true;
            }
        }
        return distancia;

    }

    private static int validar_numero() {
        boolean numero_valido = false;
        String numero = "";
        String numeros = "^[0-9]*$";
        System.out.println("Ingrese un numero valido");
        while (!numero_valido) {
            numero = edat.nextLine();
            if (numero == "" || !numero.matches(numeros)) {
                System.out.println("Formato invalido ");
            } else {
                numero_valido = true;
            }
        }
        return Integer.parseInt(numero);

    }
}
