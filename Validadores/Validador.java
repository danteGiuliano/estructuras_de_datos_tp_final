package Validadores;

import java.util.Scanner;

import ABM.AeropuertoABM;
import ABM.ClienteABM;
import ABM.VueloABM;
import Dominio.Cliente;
import Dominio.Vuelo;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Grafo.Grafo;

public class Validador {

    static Grafo mapa_aeroportuario = AeropuertoABM.mapa_aeroportuario;
    static ArbolAVL clientes =ClienteABM.clientes;
    static ArbolAVL vuelos = VueloABM.vuelos;

    /**
     * Entrada por teclado
     */
    static Scanner edat = new Scanner(System.in);

    /**
     * Formato Generales
     */
    static String numeros = "^[0-9]*$";
    static String fecha_valida = "^([1-9]|1[0-9]|2[0-9]|3[0-1])/(0?[1-9]|1[0-2])/([1-9][1-9][1-9][1-9])$";
    static String telefono_valido = "^[0-9]{3}+[-]+[0-9]{7}$";// Formato xxx-xxxxxxx
    static String hora_valida = "^((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9])$"; // Formato hh:mm

    /**
     * Formato Aeropuerto
     */
    static String nombre_valido_aeropuerto = "^([a-zA-Z]+[ ]*)*$"; // Formato de nombres
    static String codigo_valido = "^[A-Z]{3}$"; // Formato XXX

    /**
     * Formato Cliente
     * 
     */
    static String nombre_valido_cliente = "^([a-zA-Z]+[ ]*){2}$"; // Formato de nombres
    static String dni_valido = "^[A-Z]{3}$"; // Formato XXX
    static String numero_dni_valido = "^[0-9]{8}$"; // Formato xxxxxxxx 8

    /**
     * Formato de Vuelos
     * 
     */

    static String codigo_valido_vuelo = "^[A-Z]{2}[0-9]{4}$";

    /**
     * 
     * 
     * 
     * Validadores Generales
     * 
     */

    public static int validar_numero() {
        boolean numero_valido = false;
        String numero = "";
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

    public static String validar_fecha() {
        String numero_telefono = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese una fecha valida dd/mm/aaaa");
            numero_telefono = edat.nextLine();
            if (!numero_telefono.matches(fecha_valida)) {
                System.out.println("Formato de fecha invalida debe ser dd/mm/aaaa");
            } else {
                aceptado = true;
            }
        }
        return numero_telefono;
    }

    public static String validar_hora() {
        String hora = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese una hora de tipo hh:mm");
            hora = edat.nextLine();
            if (!hora.matches(hora_valida)) {
                System.out.println("Formato de fecha invalida debe ser hh:mm");
            } else {
                aceptado = true;
            }
        }
        return hora;
    }

    public static boolean validar_opcion() {
        String opcion = "^S|N$";
        String lectura = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("S/N ?");
            lectura = edat.nextLine().toUpperCase();
            if (!lectura.matches(opcion)) {
                System.out.println("Formato invalido ingrese S para Si N para No");
            } else {
                aceptado = true;
            }
        }
        if (lectura.charAt(0) == 'S') {
            aceptado = true;
        } else {
            aceptado = false;
        }
        return aceptado;
    }

    /**
     * 
     * 
     * Validadores de Aeropuerto
     */

    public static String validar_nombre_aeroportuario() {
        String nombre_aeropuerto = "";
        boolean aceptado = false;

        while (!aceptado) {
            System.out.println("Ingrese el nombre de su Aeropuerto");
            nombre_aeropuerto = edat.nextLine();
            if (!nombre_aeropuerto.matches(nombre_valido_aeropuerto)) {
                System.out.println("Su nombre no tiene un formato valido ingrese un nombre valido");
            } else {
                if (nombre_aeropuerto == "") {
                    System.out.println("Debe ingresar un nombre para su aeropuerto");
                } else {
                    aceptado = true;
                }

            }
        }
        return nombre_aeropuerto;
    }

    public static String validar_codigo_aeroportuario() {
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

    public static String validar_telefono() {
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

    public static String validar_aeropuerto() {
        String aeropuerto = "";
        boolean aceptado = false;
        while (!aceptado) {
            aeropuerto = validar_codigo_aeroportuario();
            if (!mapa_aeroportuario.existe_vertice(aeropuerto)) {
                System.out.println("No existe Aeropuerto");
            } else {
                aceptado = true;
            }
        }
        return aeropuerto;
    }

    public static String validar_ruta_aeroportuaria() {
        boolean ruta_valida = false;
        String distancia = "";

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

    /**
     * 
     * 
     * Validadores de Pasaje
     * 
     */

    public static String validar_estado_pasaje() {
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

    /**
     * 
     * Validadores Cliente
     * 
     * 
     */

    public static String validar_nombre_cliente(char tipo) {
        String nombre_aeropuerto = "";
        boolean aceptado = false;

        while (!aceptado) {
            tipo_lectura(tipo);
            nombre_aeropuerto = edat.nextLine();
            if (!nombre_aeropuerto.matches(nombre_valido_cliente)) {
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

    public static String validar_documento_cliente() {
        String documento = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese su numero de documento");
            documento = edat.nextLine();
            if (!documento.matches(numero_dni_valido)) {
                System.out.println("Formato de documento invalido debe ser xxxxxxxx");
            } else {
                aceptado = true;
            }
        }
        return documento;
    }

    public static Cliente validar_cliente() {
        boolean existe = false;
        System.out.println("Busqueda de cliente");
        String tipo_dni = "";
        String numero_documento = "";
        Cliente cliente = null;
        while (!existe) {
            tipo_dni = validar_tipo_dni_cliente();
            numero_documento = validar_documento_cliente();
            cliente = (Cliente) clientes.extraer_elemento(tipo_dni + numero_documento);
            if (cliente == null) {
                System.out.println("El cliente no existe en el registro");
            } else {
                existe = true;
            }
        }
        return cliente;
    }

    public static String validar_tipo_dni_cliente() {
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

    /**
     * 
     * Validador Vuelo
     * 
     * 
     */

    public static String validar_codigo_vuelo() {
        String codigo_vuelo = "";
        boolean aceptado = false;
        while (!aceptado) {
            System.out.println("Ingrese su codigo de vuelo EJ AA2279");
            codigo_vuelo = edat.nextLine();
            if (!codigo_vuelo.matches(codigo_valido_vuelo)) {
                System.out.println("Formato invalido");
            } else {
                aceptado = true;
            }
        }
        return codigo_vuelo;

    }

    public static String validar_vuelo() {
        Vuelo vuelo = null;
        boolean existe = false;
        System.out.println("Busqueda de Vuelo");
        String codigo_vuelo = "";
        while (!existe) {
            codigo_vuelo = validar_codigo_vuelo();
            vuelo = (Vuelo) vuelos.extraer_elemento(codigo_vuelo);
            if (vuelo == null) {
                System.out.println("El Vuelo no existe en el registro");
            } else {
                existe = true;
            }
        }
        return codigo_vuelo;
    }

}
