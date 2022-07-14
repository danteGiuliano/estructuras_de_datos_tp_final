package ABM;

import java.util.Scanner;


import Dominio.Aeropuerto;
import Estructuras.Grafo.Grafo;
import Estructuras.Lista.Lista;
import ORM.ORM;
import Validadores.Validador;

public class AeropuertoABM {

    static String format = "%20s %60s";
    static Scanner edat = new Scanner(System.in);

    static Grafo mapa_aeroportuario = ORM.get_aeropuertos();


    public static void main(String[] args) {
        menu();
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

        nombre_aeropuerto = Validador.validar_nombre_aeroportuario();
        codigo_aeropuerto =Validador.validar_codigo_aeroportuario();
        numero_telefono = Validador.validar_telefono();

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
        String codigo = "";
        codigo = Validador.validar_codigo_aeroportuario();

        Aeropuerto aeropuerto = (Aeropuerto) mapa_aeroportuario.extraer_vertice(codigo);
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
                    String codigo =Validador.validar_codigo_aeroportuario();
                    aeropuerto.set_codigo(codigo);
                    System.out.println("CODIGO MODIFICADO CON EXITO!");
                    break;
                case 2:
                    String nombre = Validador.validar_nombre_aeroportuario();
                    aeropuerto.set_nombre(nombre);
                    System.out.println("NOMBRE MODIFICADO CON EXITO!");
                    break;
                case 3:
                    String telefono = Validador.validar_telefono();
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
        String codigo = Validador.validar_codigo_aeroportuario();
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
        String aeropuerto_a = Validador.validar_aeropuerto();
        String aeropuerto_b =Validador.validar_aeropuerto();
        String ruta = Validador.validar_ruta_aeroportuaria();

        mapa_aeroportuario.insertar_arco(aeropuerto_a, aeropuerto_b, ruta);
        System.out.println("Nueva Ruta añadida");
    }

    private static void borrar_ruta() {

        String aeropuerto_a = Validador.validar_aeropuerto();
        String aeropuerto_b = Validador.validar_aeropuerto();

        String ruta = Validador.validar_ruta_aeroportuaria();
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
        String aeropuerto_a = Validador.validar_aeropuerto();
        String aeropuerto_b = Validador.validar_aeropuerto();
        Lista rutas = mapa_aeroportuario.listar_arcos(aeropuerto_a, aeropuerto_b);
        while(sesion){

        System.out.println("Rutas:");
        for (int i = 1; i <= rutas.longitud(); i++) {
            System.out.print("[ " + i + " -> " + rutas.recuperar(i) + " ],");
        }
        System.out.println("Seleccione una ruta a modificar 0 para salir");
        opcion = Validador.validar_numero();
        if (opcion != 0) {
            int distancia = 0;
            String ruta = (String) rutas.recuperar(opcion);
            System.out.println("Selecciono la ruta de :" + ruta + " hrs");
            System.out.println("Ingrese una nueva distancia , 0 para cancelar");
            distancia = Validador.validar_numero();
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

}
