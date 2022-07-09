package Estructuras.Grafo;

public class NodoAdyacente {
    private Object etiqueta;
    private NodoVertice vertice;
    private NodoAdyacente nodoAdyacente;
    
    public NodoAdyacente(Object etiqueta, NodoVertice vertice, NodoAdyacente nodoAdyacente) {
        this.etiqueta = etiqueta;
        this.vertice = vertice;
        this.nodoAdyacente = nodoAdyacente;
    }

    public NodoVertice get_vertice() {
        return vertice;
    }

    public void set_vertice(NodoVertice vertice) {
        this.vertice = vertice;
    }

    public NodoAdyacente get_nodo_adyacente() {
        return nodoAdyacente;
    }

    public void set_nodo_adyacente(NodoAdyacente nodoAdyacente) {
        this.nodoAdyacente = nodoAdyacente;
    }
    
    
}
