package Dominio;

public class Pasaje {
    String fecha;
    String estado;
    String vuelo;
    String asiento_nro;
    

    public Pasaje(String vuelo,String fecha,String asiento_nro,String estado){
        this.fecha          =   fecha;
        this.estado         =   estado;
        this.vuelo          =   vuelo;
        this.asiento_nro    =   asiento_nro;
    }

    public String get_fecha(){
        return this.fecha;
    }
    public String get_estado(){
        return this.estado;
    }
    public String get_vuelo(){
        return this.vuelo;
    }
    public String get_asiento_nro(){
        return this.asiento_nro;
    }

    public void set_fecha(String fecha){
        this.fecha=fecha;
    }
    public void set_estado(String estado){
        this.estado=estado;
    }
    public void set_vuelo(String vuelo){
        this.vuelo=vuelo;
    }
    public void set_asiento_nro(String asiento_nro){
        this.asiento_nro=asiento_nro;
    }
    

}
