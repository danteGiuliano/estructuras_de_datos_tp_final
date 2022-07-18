package Estructuras.Grafo;


class NodoAdyacente {
    
    private Comparable etiqueta;
    private NodoVertice vertice;
    private NodoAdyacente nodoAdyacente;
    
    public NodoAdyacente(NodoVertice vertice) {
        this.vertice = vertice;
        this.nodoAdyacente=null;
    }
    
     public NodoAdyacente(NodoVertice vertice,Comparable etiqueta) {
        this.vertice = vertice;
        this.etiqueta=etiqueta;
        this.nodoAdyacente=null;
    }
    public NodoAdyacente(NodoVertice vertice,NodoAdyacente etiqueta) {
        this.vertice = vertice;
        this.etiqueta=null;
        this.nodoAdyacente=etiqueta;
    }
    public NodoAdyacente(Comparable etiqueta, NodoVertice vertice, NodoAdyacente nodoAdyacente) {
        this.etiqueta = etiqueta;
        this.vertice = vertice;
        this.nodoAdyacente = nodoAdyacente;
    }
    
    public Comparable get_etiqueta() {
        return etiqueta;
    }

    public void set_etiqueta(Comparable etiqueta) {
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
    public int compararArco(Comparable objeto){
        return etiqueta.compareTo(objeto);
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((etiqueta == null) ? 0 : etiqueta.hashCode());
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
        return true;
    }
    public int peso_etiqueta(){
        return (int) etiqueta;
    }

    
}
