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

    public String get_fecha() {
        return fecha;
    }

    public void set_fecha(String fecha) {
        this.fecha = fecha;
    }

    public String get_cantidad_asientos_totales() {
        return cantidad_asientos_totales;
    }

    public void set_cantidad_asientos_totales(String cantidad_asientos_totales) {
        this.cantidad_asientos_totales = cantidad_asientos_totales;
    }

    public String get_cantidad_asientos_vendidos() {
        return cantidad_asientos_vendidos;
    }

    public void set_cantidad_asientos_vendidos(String cantidad_asientos_vendidos) {
        this.cantidad_asientos_vendidos = cantidad_asientos_vendidos;
    }
    public boolean equals(Object object){
        return this.fecha.equals(object.toString());
    }
}
