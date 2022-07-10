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
     public NodoVertice(Object elemento,NodoVertice siguiente_vertice){
        this.siguiente_vertice=siguiente_vertice;
        this.elememento=elemento;
    }

    public Object get_elememento() {
        return elememento;
    }

    public void set_elememento(Object elememento) {
        this.elememento = elememento;
    }

    public NodoVertice get_siguiente_vertice() {
        return siguiente_vertice;
    }

    public void set_siguiente_vertice(NodoVertice siguiente_vertice) {
        this.siguiente_vertice = siguiente_vertice;
    }

    public NodoAdyacente get_primer_nodo() {
        return primer_nodo;
    }

    public void set_primer_nodo(NodoAdyacente primer_nodo) {
        this.primer_nodo = primer_nodo;
    }
    @Override
    public boolean equals(Object obj) {
        return elememento.equals(obj);
    }
    @Override
    public String toString() {
        return this.elememento.toString();
    }
}
