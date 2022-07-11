package Estructuras.AVL;

public class NodoAVL {
    private Comparable elemento;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;
    
    public NodoAVL(Comparable elemento, NodoAVL nodo_izquierdo, NodoAVL nodo_derecho){
        this.elemento = elemento;
        this.izquierdo = nodo_izquierdo;
        this.derecho = nodo_derecho;
        this.altura = 0;
    }
    public NodoAVL(Comparable elemento) {
        this.derecho=null;
        this.izquierdo=null;
        this.elemento=elemento;
        this.altura=0;
    }
    
    
    public void set_elemento(Comparable elemento){
        this.elemento = elemento;
    }
    
    public Comparable get_elemento(){
        return this.elemento;
    }
    
    public int get_altura(){
        return this.altura;
    }
    public void set_izquierdo(NodoAVL nodo_izquierdo){
        this.izquierdo = nodo_izquierdo;
    }
    
    public NodoAVL get_izquierdo(){
        return this.izquierdo;
    }
    
    public void set_derecho(NodoAVL nodo_derecho){
        this.derecho = nodo_derecho;
    }
    
    public NodoAVL get_derecho(){
        return this.derecho;
    }
    
    /**
     * una vez balanceado los nodos, se debe recalcular la altura
     * 
     */
    public void recalcular_altura(){
        int altura_derecha = (this.derecho == null)? -1 : this.derecho.get_altura();
        int altura_izquierda = (this.izquierdo == null)? -1 : this.izquierdo.get_altura();
        this.altura = Math.max(altura_derecha, altura_izquierda) + 1;
    }

    
    
}
