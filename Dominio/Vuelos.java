package Dominio;

import java.lang.reflect.Array;

public class Vuelos {

    private String hora_llegada;
    private String codigo;
    private String aeropuerto_destino;
    private String hora_salida;
    private Array registro_vuelos;

    public Vuelos(String hora_llegada, String codigo, String aeropuerto_destino, String hora_salida,
            Array registro_vuelos) {

            this.hora_llegada            =   hora_llegada;
            this.codigo                  =   codigo;
            this.aeropuerto_destino      =   aeropuerto_destino;
            this.hora_salida             =   hora_salida;
            this.registro_vuelos         =   registro_vuelos;
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

    public void set_aeropuerto_destino(String aeropuerto_destino) {
        this.aeropuerto_destino = aeropuerto_destino;
    }

    public String get_hora_salida() {
        return hora_salida;
    }

    public void set_hora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Array get_registro_vuelos() {
        return registro_vuelos;
    }

    public void set_registro_vuelos(Array registro_vuelos) {
        this.registro_vuelos = registro_vuelos;
    }

}
