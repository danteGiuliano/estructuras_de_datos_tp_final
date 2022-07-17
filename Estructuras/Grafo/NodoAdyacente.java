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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((etiqueta == null) ? 0 : etiqueta.hashCode());
        result = prime * result + ((nodoAdyacente == null) ? 0 : nodoAdyacente.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NodoAdyacente other = (NodoAdyacente) obj;
        if (etiqueta == null) {
            if (other.etiqueta != null)
                return false;
        } else if (!etiqueta.equals(other.etiqueta))
            return false;
        if (nodoAdyacente == null) {
            if (other.nodoAdyacente != null)
                return false;
        } else if (!nodoAdyacente.equals(other.nodoAdyacente))
            return false;
        return true;
    }

    
}
