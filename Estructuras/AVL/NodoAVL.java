package Estructuras.AVL;

public class NodoAVL {
    private Comparable elemento;
    private NodoAVL izquirdo;
    private NodoAVL derecho;
    private int altura;
    
    public NodoAVL(Comparable argElemento, NodoAVL argNodoIzquierdo, NodoAVL argNodoDerecho){
        this.elemento = argElemento;
        this.izquirdo = argNodoIzquierdo;
        this.derecho = argNodoDerecho;
        this.altura = 0;
    }
    public NodoAVL() {

    }
    
    public void set_elemento(Comparable argElemento){
        this.elemento = argElemento;
    }
    
    public Comparable get_elemento(){
        return this.elemento;
    }
    
    public int get_altura(){
        return this.altura;
    }
    public void set_izquierdo(NodoAVL argNodoIzquierdo){
        this.izquirdo = argNodoIzquierdo;
    }
    
    public NodoAVL get_izquierdo(){
        return this.izquirdo;
    }
    
    public void set_derecho(NodoAVL argNodoDerecho){
        this.derecho = argNodoDerecho;
    }
    
    public NodoAVL get_derecho(){
        return this.derecho;
    }
    
    /**
     * 
     * una vez balanceado los nodos, se debe recalcular la altura
     * 
     */
    public void recalcular_altura(){
        int altura_derecha = (this.derecho == null)? -1 : this.derecho.get_altura();
        int altura_izquierda = (this.izquirdo == null)? -1 : this.izquirdo.get_altura();
        this.altura = Math.max(altura_derecha, altura_izquierda) + 1;
    }

    
    
}
