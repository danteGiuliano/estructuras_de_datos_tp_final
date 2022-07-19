package ABM;

import java.util.Scanner;

import Dominio.Cliente;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Lista.Lista;
import LOG.Logger;
import ORM.ORM;
import Validadores.Validador;

public class ClienteABM {
    static Scanner edat = new Scanner(System.in);
   public static ArbolAVL clientes = ORM.get_clientes();

    static String formato_cliente = "%15s %20s %15s %15s %20s %15s %15s";
    static String formato_menu = "%20s %60s";

   
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
                case 2:
                    modificar_cliente();
                    break;
                case 3:
                    borrar_cliente();
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

    private static void modificar_cliente() {

        Cliente cliente = Validador.validar_cliente();

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("                  Cliente " + cliente.get_nombre() + " " + cliente.get_apellido());
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Modificar Domicilio");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Modificar Telefono");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "Mostrar informacion");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (opcion_modificar(edat.nextLine(), cliente));

    }

    private static boolean opcion_modificar(String opcion, Cliente cliente) {
        boolean sesion = true;
        int opcion_numerica = 0;
        try {
            opcion_numerica = Integer.parseInt(opcion);
            switch (opcion_numerica) {
                case 1:
                    String domicilio = Validador.validar_nombre_cliente('D');
                    cliente.set_domicilio(domicilio);
                    System.out.println("DOMICILIO CAMBIADO CON EXITO!");
                    Logger.cliente_modificacion(cliente);
                    break;
                case 2:
                    String telefono = Validador.validar_telefono();
                    cliente.set_domicilio(telefono);
                    System.out.println("TELEFONO CAMBIADO CON EXITO!");
                    Logger.cliente_modificacion(cliente);
                    break;
                case 3:
                    mostrar_cliente(cliente);
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

    private static void cargar_cliente() {
        String nombre = Validador.validar_nombre_cliente('N');
        String apellido = Validador.validar_nombre_cliente('A');
        String domicilio = Validador.validar_nombre_cliente('D');
        String tipo_documento = Validador.validar_tipo_dni_cliente();
        String documento = Validador.validar_documento_cliente();
        String numero_telefono = Validador.validar_telefono();
        String fecha_nacimiento = Validador.validar_fecha();

        Cliente nuevo_cliente=  new Cliente(documento, nombre, apellido, tipo_documento, numero_telefono, domicilio, fecha_nacimiento);
        clientes.insertar(
                                nuevo_cliente  );
        System.out.println("CLIENTE CARGADO CON EXITO!");
        Logger.cliente_alta(nuevo_cliente);

    }

    private static void borrar_cliente() {
        boolean sesion = true;
        Cliente cliente = null;

        boolean lectura = false;
        while (sesion) {
            String tipo_documento = Validador.validar_tipo_dni_cliente();
            String numero_documento = Validador.validar_documento_cliente();
            cliente = (Cliente) clientes.extraer_elemento(tipo_documento + numero_documento);
            if (cliente != null) {
                System.out.println("Esta seguro que quiere borrar el cliente ");
                mostrar_cliente(cliente);
                lectura = Validador.validar_opcion();

                if (lectura) {
                    boolean borrado = clientes.eliminar(cliente);
                    if (borrado) {
                        System.out.println("Cliente Borrado con exito");
                        Logger.cliente_baja(cliente);
                    }
                }
                sesion = false;
            } else {
                System.out.println("Usuario invalido, desea continuar");
                sesion = Validador.validar_opcion();
            }

        }

    }

}
