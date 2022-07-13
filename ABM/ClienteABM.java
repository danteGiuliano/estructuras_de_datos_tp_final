package ABM;

import java.util.Scanner;

import Dominio.Cliente;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Lista.Lista;
import ORM.ORM;

public class ClienteABM {
    static Scanner edat = new Scanner(System.in);
    static ArbolAVL clientes = ORM.get_clientes();

    static String formato_cliente = "%15s %20s %15s %15s %20s %15s %15s";
    static String formato_menu = "%20s %60s";

    static String nombre_valido = "^([a-zA-Z]+[ ]*){2}$"; // Formato de nombres
    static String dni_valido = "^[A-Z]{3}$"; // Formato XXX
    static String numero_dni_valido = "^[0-9]{8}$"; // Formato xxxxxxxx 8
    static String telefono_valido = "^[0-9]{3}+[-]+[0-9]{7}$";// Formato xxx-xxxxxxx
    static String fecha_valida = "^([1-9]|1[0-9]|2[0-9]|3[0-1])/([1-9]|1[0-2])/([1-9][1-9][1-9][1-9])$";

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("|                                 Cliente Menu                                       |");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Cargar Cliente");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Modificar Cliente");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "Borrar Cliente");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "Mostrar Clientes");
            System.out.println("");
            System.out.format(formato_menu, "5 |", "volver atras");
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
                    cargar_cliente();
                    break;
                case 2:System.out.println(clientes.toString());
                    break;
                case 3:
                    break;
                case 4:
                    mostrar_clientes();
                    break;
                case 5:
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

    private static void mostrar_clientes() {
        Lista lista = clientes.listar();
        Cliente cliente;
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(formato_cliente, "TIPO DOCUMENTO", "NRO DOCUMENTO", "NOMBRE", "APELLIDO", "DOMICILIO",
                "TELEFONO", "FECHA NACIMIENTO");
        System.out.println();
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------");

        for (int i = 1; i <= lista.longitud(); i++) {
            cliente = (Cliente) lista.recuperar(i);
            mostrar_cliente(cliente);
        }
    }
    private static void mostrar_cliente(Cliente cliente) {
        String tipo_dni = cliente.get_tipo_dni();
        String numero_documento = cliente.get_numero_dni();
        String nombre = cliente.get_nombre();
        String apellido = cliente.get_apellido();
        String domicilio = cliente.get_domicilio();
        String telefono = cliente.get_numero_telefono();
        String fecha_nacimiento = cliente.get_fecha_nacimiento();

        System.out.format(formato_cliente, tipo_dni, numero_documento, nombre, apellido, domicilio, telefono,
                fecha_nacimiento);
        System.out.println("");
    }

    private static void cargar_cliente() {
        String nombre = validar_nombre('N');
        String apellido = validar_nombre('A');
        String domicilio = validar_nombre('D');
        String tipo_documento = validar_tipo_dni();
        String documento = validar_documento();
        String numero_telefono = validar_telefono();
        String fecha_nacimiento = validar_fecha_nacimiento();

        clientes.insertar(
                new Cliente(documento, nombre, apellido, tipo_documento, numero_telefono, domicilio, fecha_nacimiento));
        System.out.println("CLIENTE CARGADO CON EXITO!");

    }

    private static void borrar_cliente(){
        boolean existe=false;
        while(!existe){
            String tipo_documento=validar_tipo_dni();
            String numero_documento=validar_documento();
            if(true);

        }
     

    }




























    /**
     * Validadores
     * 
     */

    private static String validar_nombre(char tipo) {
        String nombre_aeropuerto = "";
        boolean aceptado = false;

        while (!aceptado) {
            tipo_lectura(tipo);
            nombre_aeropuerto = edat.nextLine();
            if (!nombre_aeropuerto.matches(nombre_valido)) {
                System.out.println("Su nombre no tiene un formato valido ingrese un nombre valido");
            } else {
                aceptado = true;
            }
        }
        return nombre_aeropuerto;
    }

    private static void tipo_lectura(char tipo) {
        switch (tipo) {
            case 'N':
                System.out.println("Ingrese un nombre valido");
                break;
            case 'A':
                System.out.println("Ingrese un Apellido valido");
                break;
            case 'D':
                System.out.println("Ingrese un Domicilio valido");
                break;
        }
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

    private static String validar_fecha_nacimiento() {
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

}
