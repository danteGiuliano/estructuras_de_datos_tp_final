package Dominio;

public class Aeropuerto {
    
    private String codigo;
    private String telefono;
    private String nombre;
    
    public Aeropuerto(String codigo, String telefono, String nombre) {
        this.codigo     = codigo;
        this.telefono   = telefono;
        this.nombre     = nombre;
    }
    
    public String get_codigo() {
        return codigo;
    }
    public void set_codigo(String codigo) {
        this.codigo = codigo;
    }
    public String get_telefono() {
        return telefono;
    }
    public void set_telefono(String telefono) {
        this.telefono = telefono;
    }
    public String get_nombre() {
        return nombre;
    }
    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }
    
}
