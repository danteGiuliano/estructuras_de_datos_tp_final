package Dominio;

import Estructuras.Lista.Lista;

public class Vuelo implements Comparable {

    private String hora_llegada;
    private String codigo;
    private String aeropuerto_destino;
    private String hora_salida;
    private String aeropuerto_origen;

    private Lista registro_vuelos;

    public Vuelo(String hora_llegada, String codigo, String aeropuerto_destino, String hora_salida,
            String aeropuerto_origen, Lista registro_vuelos) {
        this.hora_llegada = hora_llegada;
        this.codigo = codigo;
        this.aeropuerto_destino = aeropuerto_destino;
        this.hora_salida = hora_salida;
        this.aeropuerto_origen = aeropuerto_origen;
        this.registro_vuelos = registro_vuelos;
    }

    public Vuelo(String hora_llegada, String codigo, String aeropuerto_destino, String hora_salida,
            String aeropuerto_origen) {
        this.hora_llegada = hora_llegada;
        this.codigo = codigo;
        this.aeropuerto_destino = aeropuerto_destino;
        this.hora_salida = hora_salida;
        this.aeropuerto_origen = aeropuerto_origen;
        this.registro_vuelos = new Lista();
    }

    public String get_hora_llegada() {
        return hora_llegada;
    }

    public void set_hora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public String get_codigo() {
        return codigo;
    }

    public void set_codigo(String codigo) {
        this.codigo = codigo;
    }

    public String get_aeropuerto_destino() {
        return aeropuerto_destino;
    }

    public String get_aeropuerto_origen() {
        return aeropuerto_origen;
    }

    public void set_aeropuerto_origen(String aeropuerto_origen) {
        this.aeropuerto_origen = aeropuerto_origen;
    }

    public void set_aeropuerto_destino(String aeropuerto_destino) {
        this.aeropuerto_destino = aeropuerto_destino;
    }

    public String get_hora_salida() {
        return hora_salida;
    }

    public void set_hora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Lista get_registro_vuelos() {
        return registro_vuelos;
    }

    public void set_registro_vuelos(Lista registro_vuelos) {
        this.registro_vuelos = registro_vuelos;
    }

    public int hashCode() {
        int hash;
        int x = this.codigo.charAt(0);
        int y = this.codigo.charAt(1);
        int constante = Integer.parseInt(codigo.substring(1, codigo.length()));

        hash = ((y + constante) / x) - y;

        return hash;
    }

    public String toString() {
        return codigo;

    }

    public int compareTo(Object vuelo) {
        return codigo.compareTo(vuelo.toString());
    }

}
