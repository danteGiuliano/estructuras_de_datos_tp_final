package Estructuras.Lista;

public class Nodo {
    private Object elemento;
    private Nodo enlace;

    public Nodo(Object elemento,Nodo enlace){
        this.elemento=elemento;
        this.enlace=enlace;
    }

    public Object get_elemento() {
        return elemento;
    }

    public void set_elemento(Object elemento) {
        this.elemento = elemento;
    }

    public Nodo get_enlace() {
        return enlace;
    }

    public void set_enlace(Nodo enlace) {
        this.enlace = enlace;
    }
  
    
}
