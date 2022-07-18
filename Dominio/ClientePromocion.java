package Dominio;

public class ClientePromocion implements Comparable {
    Cliente cliente;
    String cantidad_pasajes;

    public ClientePromocion(Cliente cliente, String cantidad_pasajes) {
        this.cliente = cliente;
        this.cantidad_pasajes = cantidad_pasajes;
    }

    @Override
    public int compareTo(Object o) {
        return cantidad_pasajes.compareTo(o.toString());
    }

    @Override
    public String toString() {
        return "[cliente= " + cliente.toString() + "]-> "+cantidad_pasajes;
    }
    
}
