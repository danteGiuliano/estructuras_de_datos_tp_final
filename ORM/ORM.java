package ORM;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Dominio.Aeropuerto;
import Dominio.Cliente;
import Dominio.Pasaje;
import Dominio.Viaje;
import Dominio.Vuelo;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Grafo.Grafo;
import Estructuras.Lista.Lista;

public class ORM {
    /**
     * 
     * Algoritmo extra para leer/cargar/guardar informacion en el JSON
     * 
     */

    static String ruta = System.getProperty("user.dir") + "/data.json";
    static JSONParser parser = new JSONParser();
    static JSONObject data;
    static JSONArray vuelos;
    static JSONArray clientes;
    static JSONArray pasajes;
    static JSONArray rutas;
    static JSONArray aeropuertos;

    public ORM() {
    };

    static Scanner edat = new Scanner(System.in);

    private static void leer_json() throws IOException, org.json.simple.parser.ParseException {

        data = (JSONObject) parser.parse(new FileReader(ruta));
        vuelos = (JSONArray) data.get("Vuelos");
        aeropuertos = (JSONArray) data.get("Aeropuertos");
        clientes = (JSONArray) data.get("Clientes");
        pasajes = (JSONArray) data.get("Pasajes");
        rutas = (JSONArray) data.get("Rutas");

    }

    public static void main(String[] args) {
        System.out.println("Mostrar cargas");
        verifica_carga();
        mostrar_consola(vuelos);
        mostrar_consola(aeropuertos);
        mostrar_consola(rutas);
    }

    public static void cargar_info() {
        try {
            leer_json();
        } catch (FileNotFoundException f) {
        } catch (IOException e) {
            System.err.println("El archivo no existe");
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    public static void mostrar_consola(JSONArray data) {
        data.forEach(e -> {
            JSONObject elemento = (JSONObject) e;
            System.out.println(elemento);
        });
    }



    private static void guardar_info() {
        try {
            FileWriter fr = new FileWriter(ruta);
            fr.write(data.toJSONString());
            fr.flush();
            fr.close();
        } catch (FileNotFoundException f) {
        } catch (IOException e) {
            System.err.println("Archivo no existe");
        }
    }

    public static ArbolAVL get_clientes() {

        verifica_carga();

        ArbolAVL clientes_cliente = new ArbolAVL();

        clientes.forEach(e -> {

            JSONObject elemento = (JSONObject) e;
            String nombre, apellido, tipo_dni, numero_dni, domicilio, fecha_nacimiento, telefono;

            nombre = (String) elemento.get("nombre");
            apellido = (String) elemento.get("apellido");
            tipo_dni = (String) elemento.get("tipo_documento");
            numero_dni = (String) elemento.get("numero_dni");
            domicilio = (String) elemento.get("domicilio");
            telefono = (String) elemento.get("numero_telefono");
            fecha_nacimiento = (String) elemento.get("fecha_nacimiento");

            Cliente cliente = new Cliente(numero_dni, nombre, apellido, tipo_dni, telefono, domicilio,
                    fecha_nacimiento);
            clientes_cliente.insertar(cliente);
        });

        return clientes_cliente;
    }

    private static void verifica_carga() {
        if (clientes == null || vuelos == null || pasajes == null || aeropuertos == null || rutas == null) {
            cargar_info();
        }
    }

    public static Grafo get_aeropuertos() {
        Grafo mapa_aeroportuario = new Grafo();

        verifica_carga();

        aeropuertos.forEach(e -> {

            /**
             * {
             * "codigo": "NQN",
             * "telefono": "299-4792345",
             * "nombre": "Neuquen"
             * }
             */
            JSONObject elemento = (JSONObject) e;

            String codigo = (String) elemento.get("codigo");
            String telefono = (String) elemento.get("telefono");
            String nombre = (String) elemento.get("nombre");

            Aeropuerto aeropuerto = new Aeropuerto(codigo, telefono, nombre);

            if (!mapa_aeroportuario.insertar_vertice(aeropuerto)) {
                System.out.println("ERROR EN LA CARGA DE " + aeropuerto.toString() + " " + aeropuerto.get_nombre());
            }
        });

        rutas.forEach(e -> {

            JSONObject elemento = (JSONObject) e;

            String aeropuerto_a = (String) elemento.get("x");
            String aeropuerto_b = (String) elemento.get("y");
            String etiqueta = (String) elemento.get("hora");

            if (!mapa_aeroportuario.insertar_arco(aeropuerto_a, aeropuerto_b, etiqueta)) {
                System.out.println("Error en la carga Ruta " + aeropuerto_a + "-" + aeropuerto_b + "-" + etiqueta);
            }
        });

        return mapa_aeroportuario;

    }

    public static Map<Integer, Lista> get_pasajes() {
        verifica_carga();
        ArbolAVL clientes = get_clientes();
        Cliente cliente = null;
        Map<Integer, Lista> map = new HashMap();
        JSONObject elemento = null;
        Lista lista = new Lista();
        String fecha = "";
        String estado = "";
        String vuelo = "";
        String asiento_nro = "";
        String tipo_documento = "";
        String numero_documento = "";
        for (int i = 0; i < pasajes.size(); i++) {
            elemento = (JSONObject) pasajes.get(i);
            lista = new Lista();

            fecha = (String) elemento.get("fecha");
            estado = (String) elemento.get("estado");
            vuelo = (String) elemento.get("vuelo");
            asiento_nro = (String) elemento.get("asiento_nro");
            tipo_documento = (String) elemento.get("tipo_documento");
            numero_documento = (String) elemento.get("numero_dni");

            String key = tipo_documento + numero_documento;
            Pasaje p = new Pasaje(vuelo, fecha, asiento_nro, estado);
            cliente = (Cliente) clientes.extraer_elemento(key);
            if (map.get(cliente.hashCode()) != null) {
                lista = map.get(cliente.hashCode());
                lista.insertar(p, 1);
                map.put(cliente.hashCode(), lista);
            } else {
                lista.insertar(p, 1);
                if (cliente != null) {
                    map.put(cliente.hashCode(), lista);
                }
            }

        }

        return map;
    }

    public static ArbolAVL get_vuelos() {
        verifica_carga();
        ArbolAVL vuelos = new ArbolAVL();
        Lista registro_vuelos = null;
        Viaje viaje = null;
        Vuelo vuelo=null;

        JSONArray lista = null;
        JSONObject objeto = null;
        JSONObject ls = null;
        String hora_llegada = "";
        String codigo = "";
        String hora_salida = "";
        String fecha = "";
        String cantidad_asientos_vendidos = "";
        String cantidad_asientos_totales = "";
        String aeropuerto_origen = "";
        String aeropuerto_destino = "";

        for (int i = 0; i < ORM.vuelos.size(); i++) {
            
            objeto = (JSONObject) ORM.vuelos.get(i);
            codigo = (String) objeto.get("codigo");
            hora_salida = (String) objeto.get("hora_salida");
            aeropuerto_destino = (String) objeto.get("aeropuerto_destino");
            hora_llegada = (String) objeto.get("hora_llegada");
            aeropuerto_origen = (String) objeto.get("aeropuerto_origen");
            lista = (JSONArray) objeto.get("registro_vuelos");

            if (lista != null) {
                registro_vuelos = new Lista();
                for (int j = 0; j < lista.size(); j++) {
                    ls = (JSONObject) lista.get(j);
                    fecha = (String) ls.get("fecha");
                    cantidad_asientos_vendidos = (String) ls.get("cantidad_asientos_vendidos");
                    cantidad_asientos_totales = (String) ls.get("cantidad_asientos_totales");
                    viaje = new Viaje(fecha, cantidad_asientos_totales, cantidad_asientos_vendidos);
                    registro_vuelos.insertar(viaje, 1);
                }
            }
            vuelo= new Vuelo(hora_llegada, codigo, aeropuerto_destino, hora_salida, aeropuerto_origen, registro_vuelos);
            vuelos.insertar(vuelo);
        }

        return vuelos;
    }

}
