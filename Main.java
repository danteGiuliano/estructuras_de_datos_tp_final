import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ABM.AeropuertoABM;
import ABM.ClienteABM;
import ABM.PasajeABM;
import ABM.VueloABM;
import Dominio.Aeropuerto;
import Dominio.Cliente;
import Dominio.Pasaje;
import Dominio.Viaje;
import Dominio.Vuelo;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Grafo.Grafo;
import Estructuras.Lista.Lista;
import ORM.ORM;
import Validadores.Validador;

public class Main {

    static String formato_menu = "%20s %60s";
    static String formato_pasajes = "%20s %30s %30s";
    static Scanner edat = new Scanner(System.in);

    static ArbolAVL clientes = ORM.get_clientes();
    static Map<Integer, Lista> pasajes = ORM.get_pasajes();
    static Grafo aeropuerto = ORM.get_aeropuertos();
    static ArbolAVL vuelos = ORM.get_vuelos();

    public static void main(String[] args) {
        menu_principal();
   }

    public static void menu_principal() {
        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("|                                 EDAT VIajes 2020                                   |");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "ABM Aeropuerto");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "ABM Cliente");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "ABM Pasaje");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "ABM Vuelo");
            System.out.println("");
            System.out.format(formato_menu, "5 |", "Listar pasajes pendientes de un cliente");
            System.out.println("");
            System.out.format(formato_menu, "6 |", "Listar Nombres de ciudades visitadas por un cliente");
            System.out.println("");
            System.out.format(formato_menu, "7 |", "Mostrar informacion de vuelo");
            System.out.println("");
            System.out.format(formato_menu, "8 |", "Listar rango de codigos de vuelo entre menor y mayor");
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
                    AeropuertoABM.menu();
                    break;
                case 2:
                    ClienteABM.menu();
                    break;
                case 3:
                    PasajeABM.menu();
                    break;
                case 4:
                    VueloABM.menu();
                    break;
                case 5:
                    mostrar_vuelos_pendientes_cliente();
                    break;
                case 6:
                    mostrar_ciudades_visitadas_cliente();
                    break;
                case 7:
                    mostrar_informacion_de_vuelo();
                    break;
                    case 8:
                    listar_rango_menor_mayor();
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

    public static void mostrar_vuelos_pendientes_cliente() {
        Pasaje pasaje;
        int indice = 1;
        Cliente cliente = Validador.validar_cliente();
        Lista lista_pasajes_pendientes = pasajes.get(cliente.hashCode()).clone();

        System.out.println("_____________________________________________________________________________________");
        System.out
                .println("                " + cliente.get_nombre() + " " + cliente.get_apellido()
                        + " Pasajes Pendientes");
        System.out.println("_____________________________________________________________________________________");
        System.out.printf(formato_pasajes, "N°", "FECHA", "CODIGO");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");
        for (int i = 1; i <= lista_pasajes_pendientes.longitud(); i++) {
            pasaje = (Pasaje) lista_pasajes_pendientes.recuperar(i);

            if (pasaje.get_estado().equals("pendiente")) {
                System.out.printf(formato_pasajes, indice + " ", pasaje.get_fecha(), pasaje.get_vuelo());
                System.out.println("");
                indice++;
            }
        }

    }

    public static void mostrar_ciudades_visitadas_cliente() {
        Cliente cliente = Validador.validar_cliente();
        Lista lista_pasajes = pasajes.get(cliente.hashCode()).clone();
        Vuelo vuelo;
        Aeropuerto aeropuerto;

        Map<String, String> codigos = new HashMap<>();
        Map<String, String> codigo_aeropuerto = new HashMap<>();
        Map<String, String> nombre_ciudades = new HashMap<>();

        Pasaje pasaje = null;

        for (int i = 1; i <= lista_pasajes.longitud(); i++) {
            pasaje = (Pasaje) lista_pasajes.recuperar(i);
            codigos.put(pasaje.get_vuelo(), pasaje.get_vuelo());
        }

        for (String codigo : codigos.values()) {
            vuelo = (Vuelo) vuelos.extraer_elemento(codigo);
            codigo_aeropuerto.put(vuelo.get_aeropuerto_origen(), vuelo.get_aeropuerto_origen());
            codigo_aeropuerto.put(vuelo.get_aeropuerto_destino(), vuelo.get_aeropuerto_destino());
        }
        for (String codigo_aeroportuario : codigo_aeropuerto.values()) {
            aeropuerto = (Aeropuerto) Main.aeropuerto.extraer_vertice(codigo_aeroportuario);
            nombre_ciudades.put(aeropuerto.get_nombre(), aeropuerto.get_nombre());
        }

        System.out.println("_____________________________________________________________________________________");
        System.out
                .println("                " + cliente.get_nombre() + " " + cliente.get_apellido()
                        + " Pasajes Pendientes");
        System.out.println("_____________________________________________________________________________________");

        for (String nombre_ciudad : nombre_ciudades.values()) {
            System.out.println("Ciudad :" + nombre_ciudad);
        }

    }

    public static void mostrar_informacion_de_vuelo() {
        String codigo_vuelo = Validador.validar_vuelo();
        Vuelo vuelo = (Vuelo) vuelos.extraer_elemento(codigo_vuelo);
        Lista lista_vuelos = vuelo.get_registro_vuelos();
        boolean intento=true;

        while(intento){
            String fecha = Validador.validar_fecha();
            int indice = lista_vuelos.localizar(fecha);
            if (indice == -1) {
                System.out.println("No existe Vuelo con esa fecha desea probar con otra?");
                intento=Validador.validar_opcion();
    
            } else {
                Viaje viaje = (Viaje) lista_vuelos.recuperar(indice);
                System.out.println("_____________________________________________________________________________________");
                System.out.println("|                      Informacion      vuelo                                       |");
                System.out.println("_____________________________________________________________________________________");
                System.out.println("CODIGO : " + vuelo.get_codigo());
                System.out.println("AEROPUERTO ORIGEN : " + vuelo.get_aeropuerto_origen());
                System.out.println("AEROPUERTO DESTINO : " + vuelo.get_aeropuerto_destino());
                System.out.println("HORA SALIDA : " + vuelo.get_hora_salida());
                System.out.println("HORA LLEGADA : " + vuelo.get_hora_llegada());
                System.out.println("FECHA : " + viaje.get_fecha());
                System.out.println("CANTIDAD ASIENTOS VENDIDOS : " + viaje.get_cantidad_asientos_vendidos());
                System.out.println("CANTIDAD ASIENTOS TOTALES : " + viaje.get_cantidad_asientos_totales());
                intento=false;
            }
    
        }
       
    }

    public static void listar_rango_menor_mayor(){

        String vuelo_1=Validador.validar_vuelo();
        String vuelo_2=Validador.validar_vuelo();

        System.out.println("Los codigos existentes son");
        System.out.println(vuelos.listarRango(vuelo_1, vuelo_2));
        
    }

}
