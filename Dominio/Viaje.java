package Dominio;

public class Viaje {
    private String fecha;
    private String cantidad_asientos_totales;
    private String cantidad_asientos_vendidos;
    
    public Viaje(String fecha, String cantidad_asientos_totales, String cantidad_asientos_vendidos) {
        this.fecha = fecha;
        this.cantidad_asientos_totales = cantidad_asientos_totales;
        this.cantidad_asientos_vendidos = cantidad_asientos_vendidos;
    }
}
