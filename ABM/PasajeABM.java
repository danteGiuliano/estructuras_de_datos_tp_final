package ABM;

import java.util.Map;
import java.util.Scanner;

import Dominio.Cliente;
import Dominio.Pasaje;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Lista.Lista;
import ORM.ORM;

public class PasajeABM {
    // "El hash map se define para los pasajes la clave son los clientes cada"

    // HashMap claveCliente , Lista pasajes
    static Scanner edat = new Scanner(System.in);
    static String formato_pasaje = "%20s %20s %20s %20s";
    static String formato_pasaje_opcion = "%10s %20s %20s %20s %20s";
    static String formato_menu = "%20s %60s";

    static String dni_valido = "^[A-Z]{3}$"; // Formato XXX
    static String numero_dni_valido = "^[0-9]{8}$"; // Formato xxxxxxxx 8
    static String fecha_valida = "^([1-9]|1[0-9]|2[0-9]|3[0-1])/([1-9]|1[0-2])/([1-9][1-9][1-9][1-9])$";

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
        Cliente cliente = validar_cliente();
        String fecha = validar_fecha();
        String estado = "pendiente";
        // String vuelo = validar_vuelo();

    }

    public static void ver_pasajes_cliente() {
        Cliente cliente = validar_cliente();
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
        Cliente cliente = validar_cliente();
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
                    pasaje.set_estado(validar_estado_pasaje());
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

    /**
     * 
     * Validadores
     * 
     * 
     */

    private static Cliente validar_cliente() {
        boolean existe = false;
        System.out.println("Busqueda de cliente");
        String tipo_dni = "";
        String numero_documento = "";
        Cliente cliente = null;
        while (!existe) {
            tipo_dni = validar_tipo_dni();
            numero_documento = validar_documento();
            cliente = (Cliente) clientes.extraer_elemento(tipo_dni + numero_documento);
            if (cliente == null) {
                System.out.println("El cliente no existe en el registro");
            } else {
                existe = true;
            }
        }
        return cliente;
    }

    private static String validar_documento() {
        String numero_telefono = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese su numero de documento");
            numero_telefono = edat.nextLine();
            if (!numero_telefono.matches(numero_dni_valido)) {
                System.out.println("Formato de documento invalido debe ser xxxxxxxx");
            } else {
                aceptado = true;
            }
        }
        return numero_telefono;
    }

    private static String validar_tipo_dni() {
        String codigo_aeropuerto = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese su tipo de docuemento 3 letras ej : DNI");
            codigo_aeropuerto = edat.nextLine();
            if (!codigo_aeropuerto.matches(dni_valido)) {
                System.out.println("Formato invalido");
            } else {
                aceptado = true;
            }
        }
        return codigo_aeropuerto;
    }

    private static String validar_fecha() {
        String numero_telefono = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese una fecha de nacimiento");
            numero_telefono = edat.nextLine();
            if (!numero_telefono.matches(fecha_valida)) {
                System.out.println("Formato de fecha invalida debe ser dd/mm/aaaa");
            } else {
                aceptado = true;
            }
        }
        return numero_telefono;
    }

    private static String validar_estado_pasaje() {
        String validacion = "^pendiente|candelado|expirado$";
        String estado = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese un estado de pasaje ");
            estado = edat.nextLine().toLowerCase();
            if (!estado.matches(validacion)) {
                System.out.println("Formato de estado invalido, debe ser Pendiente Cancelado o Expirado");
            } else {
                aceptado = true;
            }
        }
        return estado;
    }

}
