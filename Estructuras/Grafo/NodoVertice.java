package Estructuras.Grafo;

public class NodoVertice {

    private Object elememento;
    private NodoVertice siguiente_vertice;
    private NodoAdyacente primer_nodo;

    public NodoVertice(Object elememento, NodoVertice siguiente_vertice, NodoAdyacente primer_nodo) {
        this.elememento = elememento;
        this.siguiente_vertice = siguiente_vertice;
        this.primer_nodo = primer_nodo;
    }

    public NodoVertice(Object elemento){
        this.elememento=elemento;
    }

    public Object getElememento() {
        return elememento;
    }

    public void setElememento(Object elememento) {
        this.elememento = elememento;
    }

    public NodoVertice getSiguiente_vertice() {
        return siguiente_vertice;
    }

    public void setSiguiente_vertice(NodoVertice siguiente_vertice) {
        this.siguiente_vertice = siguiente_vertice;
    }

    public NodoAdyacente getPrimer_nodo() {
        return primer_nodo;
    }

    public void setPrimer_nodo(NodoAdyacente primer_nodo) {
        this.primer_nodo = primer_nodo;
    }
    
}
