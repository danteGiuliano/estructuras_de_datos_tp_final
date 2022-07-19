package LOG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import ABM.AeropuertoABM;
import ABM.ClienteABM;
import ABM.PasajeABM;
import ABM.VueloABM;
import Dominio.Aeropuerto;
import Dominio.Cliente;
import Dominio.Pasaje;
import Dominio.Vuelo;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Grafo.Grafo;
import Estructuras.Lista.Lista;

public class Logger {
    static File log = new File("LOG\\log.txt");
    static String data = "";

    static ArbolAVL clientes = ClienteABM.clientes;
    static Map<Integer, Lista> pasajes = PasajeABM.pasajes;
    static Grafo aeropuerto = AeropuertoABM.mapa_aeroportuario;
    static ArbolAVL vuelos = VueloABM.vuelos;

    private static void create_log() {
        try {
            if (log.createNewFile()) {
                System.out.println("Archivo creado: " + log.getName());
            }
        } catch (IOException e) {
            System.out.println("Error generico.");
            e.printStackTrace();
        }
    }

    public static void iniciar_log() {
        create_log();
        try {
            File log = new File("LOG\\log.txt");
            Scanner edat = new Scanner(log);
            while (edat.hasNextLine()) {
                String data = edat.nextLine();
            }
            edat.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error leyendo el log.");
            e.printStackTrace();
        }
    }


    private  static void escribir_log(String cadena) {
       data+="\n"+cadena+"\n";
        try {
            FileWriter myWriter = new FileWriter("LOG\\log.txt");
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo.");
            e.printStackTrace();
        }
    }

    public static void aeropuerto_alta(Aeropuerto aeropuerto) {
        escribir_log("Alta en " + aeropuerto.log());
    }

    public static void aeropuerto_modificacion(Aeropuerto aeropuerto) {
        escribir_log("Modificacion en " + aeropuerto.log());
    }

    public static void aeropuerto_baja(Aeropuerto aeropuerto) {
        escribir_log("Baja en " + aeropuerto.log());
    }

    public static void cliente_alta(Cliente cliente) {
        escribir_log("Alta en " + cliente.log());
    }

    public static void cliente_modificacion(Cliente cliente) {
        escribir_log("Modificaion en " + cliente.log());
    }

    public static void cliente_baja(Cliente cliente) {
        escribir_log("Baja en " + cliente.log());
    }

    public static void Pasaje_alta(Pasaje pasaje) {
        escribir_log("Alta en " + pasaje.log());
    }

    public static void Pasaje_modificacion(Pasaje pasaje) {
        escribir_log("Modificacion en " + pasaje.log());
    }

    public static void Pasaje_baja(Pasaje pasaje) {
        escribir_log("Baja en " + pasaje.log());
    }

    public static void Vuelo_alta(Vuelo vuelo) {
        escribir_log("Alta en " + vuelo.log());
    }

    public static void Vuelo_modificacion(Vuelo vuelo) {
        escribir_log("Modificacion en " + vuelo.log());
    }

    public static void Vuelo_baja(Vuelo vuelo) {
        escribir_log("Baja en " + vuelo.log());
    }

    public static void mostrar_estructuras() {
        try {
            escribir_log(
                    "Aeropuertos " + aeropuerto.toString() + " \n Clientes " + clientes.toString() + "\n Pasajes " +
                            pasajes.toString() + "\n Vuelos " + vuelos.toString() + "\n");

        } catch (Exception e) {

        }

    }

}
