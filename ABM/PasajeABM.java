package ABM;

import java.util.Map;
import java.util.Scanner;

import Dominio.Cliente;
import Dominio.Pasaje;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Lista.Lista;
import ORM.ORM;
import Validadores.Validador;

public class PasajeABM {
    // "El hash map se define para los pasajes la clave son los clientes cada"

    // HashMap claveCliente , Lista pasajes
    static Scanner edat = new Scanner(System.in);
    static String formato_pasaje = "%20s %20s %20s %20s";
    static String formato_pasaje_opcion = "%10s %20s %20s %20s %20s";
    static String formato_menu = "%20s %60s";

    

    static Map<Integer, Lista> map = ORM.get_pasajes();
    static ArbolAVL clientes = ORM.get_clientes();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("|                                 Pasaje Menu                                        |");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Comprar pasaje");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Seleccionar pasaje");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "Ver pasajes Cliente");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "volver atras");
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
                    comprar_pasaje();
                    break;
                case 2:
                    modificar_pasaje();
                    break;
                case 3:
                    ver_pasajes_cliente();
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

    public static void comprar_pasaje() {
        Cliente cliente = Validador.validar_cliente();;
        String fecha =Validador.validar_fecha();
        String estado = "pendiente";
        // String vuelo = validar_vuelo();

    }

    public static void ver_pasajes_cliente() {
        Cliente cliente = Validador.validar_cliente();
        Lista pasajes = map.get(cliente.hashCode());
        if (pasajes != null) {
            System.out.println(
                    "-------------------------------------------------------------------------------------------------");
            System.out.printf(formato_pasaje, "FECHA", " ESTADO", "VUELO", "ASIENTO NUMERO");
            System.out.println();
            System.out.println(
                    "-------------------------------------------------------------------------------------------------");
            for (int i = 1; i <= pasajes.longitud(); i++) {
                mostrar_pasaje((Pasaje) pasajes.recuperar(i));
            }
        }

    }

    private static void mostrar_pasaje(Pasaje pasaje) {
        String fecha = pasaje.get_fecha();
        String estado = pasaje.get_estado();
        String vuelo = pasaje.get_vuelo();
        String asiento_nro = pasaje.get_asiento_nro();
        System.out.format(formato_pasaje, fecha, estado, vuelo, asiento_nro);
        System.out.println("");
    }

    public static void modificar_pasaje() {
        Cliente cliente = Validador.validar_cliente();
        int opcion = 0;
        boolean sesion=true;
        while (sesion) {
            System.out.println("Seleccione el pasaje de la lista 0 para salir");
            Lista pasajes = map.get(cliente.hashCode());
            System.out.println(
                    "--------------------------------------------------------------------------------------");
            System.out.printf(formato_pasaje_opcion, "N°", "FECHA", " ESTADO", "VUELO", "ASIENTO NUMERO");
            System.out.println();
            System.out.println(
                    "--------------------------------------------------------------------------------------");
            for (int i = 1; i <= pasajes.longitud(); i++) {
                mostrar_pasaje_opcion((Pasaje) pasajes.recuperar(i), i);
            }
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
            opcion = Integer.parseInt(edat.nextLine());
            if (opcion != 0) {
                Pasaje pasaje = (Pasaje) pasajes.recuperar(opcion);
                if (pasaje != null) {
                    pasaje_modificar(pasaje, cliente, pasajes);
                } else {
                    System.out.println("El pasaje no existe");
                }
            }else{
                sesion=false;
            }
        }
    }

    private static void mostrar_pasaje_opcion(Pasaje pasaje, int indice) {
        String fecha = pasaje.get_fecha();
        String estado = pasaje.get_estado();
        String vuelo = pasaje.get_vuelo();
        String asiento_nro = pasaje.get_asiento_nro();
        System.out.format(formato_pasaje_opcion, indice, fecha, estado, vuelo, asiento_nro);
        System.out.println("");

    }

    private static void pasaje_modificar(Pasaje pasaje, Cliente cliente, Lista pasajes) {
        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("                  Cliente " + cliente.get_nombre() + " " + cliente.get_apellido());
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Modificar Estado pasaje");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Ver informacion pasaje");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "Eliminar pasaje");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (opcion_modificar(edat.nextLine(), pasaje, pasajes));

    }

    private static boolean opcion_modificar(String opcion, Pasaje pasaje, Lista pasajes) {
        boolean sesion = true;
        int opcion_numerica = 0;
        try {
            opcion_numerica = Integer.parseInt(opcion);
            switch (opcion_numerica) {
                case 1:
                    pasaje.set_estado(Validador.validar_estado_pasaje());
                    System.out.println("Pasaje modificado cone exito");
                    break;
                case 2:
                    mostrar_pasaje(pasaje);
                    break;
                case 3:
                    pasajes.eliminar(pasajes.localizar(pasaje));
                    System.out.println("PASAJE BORRADO CON EXITO");
                    sesion = false;
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

  

}
