package Estructuras.Grafo;


class NodoAdyacente {
    
    private Object etiqueta;
    private NodoVertice vertice;
    private NodoAdyacente nodoAdyacente;
    
    public NodoAdyacente(NodoVertice vertice) {
        this.vertice = vertice;
        this.nodoAdyacente=null;
    }
    
     public NodoAdyacente(NodoVertice vertice,Object etiqueta) {
        this.vertice = vertice;
        this.etiqueta=etiqueta;
        this.nodoAdyacente=null;
    }
    public NodoAdyacente(NodoVertice vertice,NodoAdyacente etiqueta) {
        this.vertice = vertice;
        this.etiqueta=etiqueta;
        this.nodoAdyacente=null;
    }
    public NodoAdyacente(Object etiqueta, NodoVertice vertice, NodoAdyacente nodoAdyacente) {
        this.etiqueta = etiqueta;
        this.vertice = vertice;
        this.nodoAdyacente = nodoAdyacente;
    }
    
    public Object get_etiqueta() {
        return etiqueta;
    }

    public void set_etiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
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
